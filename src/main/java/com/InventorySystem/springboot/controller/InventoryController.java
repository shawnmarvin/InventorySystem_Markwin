package com.InventorySystem.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.InventorySystem.springboot.model.Inventory;
import com.InventorySystem.springboot.service.InventoryService;

@RestController
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@GetMapping("/inventory")
	public ResponseEntity<List<Inventory>> getAllInventory(){
		return ResponseEntity.ok().body(inventoryService.getAllInventory());
	}

	@GetMapping("/inventory/{id}")
	public ResponseEntity<Inventory> getInventoryById(@PathVariable long id){
		return ResponseEntity.ok().body(inventoryService.getInventoryById(id));
	}

	@PostMapping("/inventory")
	public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory){
		return ResponseEntity.ok().body(this.inventoryService.createInventory(inventory));
	}

	@PutMapping("/inventory/{id}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable long id, @RequestBody Inventory inventory){
		inventory.setId(id);
		return ResponseEntity.ok().body(this.inventoryService.updateInventory(inventory));
	}

	@DeleteMapping("/inventory/{id}")
	public HttpStatus deleteEmployee(@PathVariable long id){
		this.inventoryService.deleteInventory(id);
		return HttpStatus.OK;
	}
}