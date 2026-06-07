package com.omak.orderbilling.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_settings")
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String storeName;

    private String contactEmail;

    private String currency;

    private String taxRate;

    private Boolean orderNotifications;

    private Boolean lowStockAlerts;

    private Boolean twoFactor;

    private String avatar;

    public Setting() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public Boolean getOrderNotifications() {
        return orderNotifications;
    }

    public void setOrderNotifications(Boolean orderNotifications) {
        this.orderNotifications = orderNotifications;
    }

    public Boolean getLowStockAlerts() {
        return lowStockAlerts;
    }

    public void setLowStockAlerts(Boolean lowStockAlerts) {
        this.lowStockAlerts = lowStockAlerts;
    }

    public Boolean getTwoFactor() {
        return twoFactor;
    }

    public void setTwoFactor(Boolean twoFactor) {
        this.twoFactor = twoFactor;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
