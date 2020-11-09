package com.alissondev.nuinfo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alissondev.nuinfo.entities.People;
import com.alissondev.nuinfo.services.PeopleService;

@RestController
@RequestMapping("/peoples")
public class PeopleController {
	
	@Autowired
	private PeopleService service;

	@GetMapping
	public ResponseEntity<List<People>> findAll() {						
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<People> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<People> insert(@RequestBody People people) {
		people = service.insert(people);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand(people.getId()).toUri();
		
		return ResponseEntity.created(uri).body(people);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<People> update(@PathVariable Long id, @RequestBody People people) {
		people = service.update(id, people);		
		return ResponseEntity.ok(people);				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
