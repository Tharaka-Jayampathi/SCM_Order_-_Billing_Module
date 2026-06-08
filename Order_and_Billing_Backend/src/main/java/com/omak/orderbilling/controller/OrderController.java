package com.omak.orderbilling.controller;

import com.omak.orderbilling.entity.Order;
import com.omak.orderbilling.entity.OrderItem;
import com.omak.orderbilling.entity.Product;
import com.omak.orderbilling.repository.OrderRepository;
import com.omak.orderbilling.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        BigDecimal total = BigDecimal.ZERO;
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                item.setOrder(order);
                // Look up the product to get the current price
                if (item.getProduct() != null && item.getProduct().getId() != null) {
                    Product product = productRepository.findById(item.getProduct().getId()).orElse(null);
                    if (product != null) {
                        item.setUnitPrice(product.getPrice());
                        item.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                        // Reduce stock
                        product.setStock(product.getStock() - item.getQuantity());
                        productRepository.save(product);
                        item.setProduct(product);
                    }
                }
                total = total.add(item.getSubtotal());
            }
        }
        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomer(orderDetails.getCustomer());
                    order.setTotalAmount(orderDetails.getTotalAmount());
                    order.setStatus(orderDetails.getStatus());
                    // Don't update orderDate unless explicitly needed
                    return ResponseEntity.ok(orderRepository.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
