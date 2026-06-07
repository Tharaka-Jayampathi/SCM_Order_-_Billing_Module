package com.omak.orderbilling.service;

import com.omak.orderbilling.entity.Setting;
import com.omak.orderbilling.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    private SettingRepository repository;

    public Setting save(Setting setting) {
        return repository.save(setting);
    }

    public Setting getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Setting update(Setting setting) {
        return repository.save(setting);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
