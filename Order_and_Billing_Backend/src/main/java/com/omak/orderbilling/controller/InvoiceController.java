package com.omak.orderbilling.controller;

import com.omak.orderbilling.entity.Invoice;
import com.omak.orderbilling.entity.Order;
import com.omak.orderbilling.repository.InvoiceRepository;
import com.omak.orderbilling.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    @Transactional(readOnly = true)
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        // Force load nested customer data
        for (Invoice invoice : invoices) {
            if (invoice.getOrder() != null && invoice.getOrder().getCustomer() != null) {
                invoice.getOrder().getCustomer().getName();
            }
        }
        return invoices;
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceRepository.findById(id)
                .map(invoice -> {
                    if (invoice.getOrder() != null && invoice.getOrder().getCustomer() != null) {
                        invoice.getOrder().getCustomer().getName();
                    }
                    return ResponseEntity.ok(invoice);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        // Load the full order with customer
        if (invoice.getOrder() != null && invoice.getOrder().getId() != null) {
            Order order = orderRepository.findById(invoice.getOrder().getId()).orElse(null);
            if (order != null) {
                invoice.setOrder(order);
                if (invoice.getAmount() == null) {
                    invoice.setAmount(order.getTotalAmount());
                }
            }
        }
        return invoiceRepository.save(invoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoiceDetails) {
        return invoiceRepository.findById(id)
                .map(invoice -> {
                    invoice.setOrder(invoiceDetails.getOrder());
                    invoice.setIssueDate(invoiceDetails.getIssueDate());
                    invoice.setDueDate(invoiceDetails.getDueDate());
                    invoice.setAmount(invoiceDetails.getAmount());
                    invoice.setStatus(invoiceDetails.getStatus());
                    return ResponseEntity.ok(invoiceRepository.save(invoice));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
