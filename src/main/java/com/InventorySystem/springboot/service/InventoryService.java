package com.InventorySystem.springboot.service;

import java.util.List;

import com.InventorySystem.springboot.model.Inventory;

public interface InventoryService {
	Inventory createInventory(Inventory inventory);

	Inventory updateInventory(Inventory inventory);

	List<Inventory> getAllInventory();

	Inventory getInventoryById(long inventoryId);

	void deleteInventory(long id);
}