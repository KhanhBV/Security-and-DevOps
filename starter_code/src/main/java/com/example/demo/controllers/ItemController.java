package com.example.demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;

@RestController
@RequestMapping("/api/item")
public class ItemController {
	static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping
	public ResponseEntity<List<Item>> getItems() {
		List<Item> list = itemRepository.findAll();
		if (list.isEmpty()) {
			logger.info("Get all item is failed!");
		}

		return ResponseEntity.ok(itemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable Long id) {
		boolean isItem = itemRepository.findById(id).isPresent();
		if (isItem) {
			return ResponseEntity.of(itemRepository.findById(id));
		}
		logger.error("Can not find item by id {}", id);
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Item>> getItemsByName(@PathVariable String name) {
		List<Item> items = itemRepository.findByName(name);
		if (items == null || items.isEmpty()) {
			logger.error("Can not find item by name {}", name);
			ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(items);
	}
	
}
