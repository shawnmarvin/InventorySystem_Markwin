package com.InventorySystem.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.InventorySystem.springboot.exception.ResourceNotFoundException;
import com.InventorySystem.springboot.model.Inventory;
import com.InventorySystem.springboot.repository.InventoryRepository;


@Service
@Transactional
public class InventoryServiceImpl implements InventoryService{


	@Autowired
	private InventoryRepository inventoryRepository;


	@Override
	public Inventory createInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	@Override
	public Inventory updateInventory(Inventory inventory) {
		Optional<Inventory> inventoryDb = this.inventoryRepository.findById(inventory.getId());

		if(inventoryDb.isPresent()) {
			Inventory inventoryUpdate = inventoryDb.get();
			inventoryUpdate.setId(inventory.getId());
			inventoryUpdate.setEmployeeNumber(inventory.getemployeeNumber());
			inventoryUpdate.setEmployeeName(inventory.getEmployeeName());
			inventoryUpdate.setItemType(inventory.getItemType());
			inventoryUpdate.setItemBrand(inventory.getItemBrand());
			inventoryUpdate.setBarcode(inventory.getBarcode());
			inventoryUpdate.setSerialNumber(inventory.getSerialNumber());
			inventoryRepository.save(inventoryUpdate);
			return inventoryUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + inventory.getId());
		}		
	}

	@Override
	public List<Inventory> getAllInventory() {
		return this.inventoryRepository.findAll();
	}

	@Override
	public Inventory getInventoryById(long inventoryId) {

		Optional<Inventory> inventoryDb = this.inventoryRepository.findById(inventoryId);

		if(inventoryDb.isPresent()) {
			return inventoryDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + inventoryId);
		}
	}

	@Override
	public void deleteInventory(long inventoryId) {
		Optional<Inventory> inventoryDb = this.inventoryRepository.findById(inventoryId);

		if(inventoryDb.isPresent()) {
			this.inventoryRepository.delete(inventoryDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + inventoryId);
		}

	}

}