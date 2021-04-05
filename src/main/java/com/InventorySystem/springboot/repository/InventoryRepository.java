package com.InventorySystem.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.InventorySystem.springboot.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{

}