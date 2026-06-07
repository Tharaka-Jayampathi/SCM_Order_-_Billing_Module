package com.omak.orderbilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.omak.orderbilling.entity.Setting;
import com.omak.orderbilling.service.SettingService;

import java.io.File;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "http://localhost:5173")
public class SettingsController {

    @Autowired
    private SettingService service;

    @PutMapping(
            value="/update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )

    public ResponseEntity<String> saveSettings(

            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String storeName,
            @RequestParam String contactEmail,
            @RequestParam String currency,
            @RequestParam String taxRate,
            @RequestParam Boolean orderNotifications,
            @RequestParam Boolean lowStockAlerts,
            @RequestParam Boolean twoFactor,
            @RequestParam(required = false)
            MultipartFile avatar

    ) throws Exception {

        Setting settings =
                new Setting();

        settings.setFullName(fullName);
        settings.setEmail(email);
        settings.setStoreName(storeName);
        settings.setContactEmail(contactEmail);
        settings.setCurrency(currency);
        settings.setTaxRate(taxRate);
        settings.setOrderNotifications(
                orderNotifications);
        settings.setLowStockAlerts(
                lowStockAlerts);
        settings.setTwoFactor(
                twoFactor);

        if (avatar != null) {

            String fileName =
                    avatar.getOriginalFilename();

            avatar.transferTo(
                    new File(
                            "uploads/"
                            + fileName
                    )
            );

            settings.setAvatar(fileName);
        }

        service.save(settings);

        return ResponseEntity.ok(
                "Settings Saved Successfully"
        );
    }
}
