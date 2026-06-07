package com.omak.orderbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.omak.orderbilling.entity.Setting;

@Repository
public interface SettingRepository
extends JpaRepository<Setting,Long>{

}
