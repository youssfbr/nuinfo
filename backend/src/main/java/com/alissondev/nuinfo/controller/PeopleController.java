package com.alissondev.nuinfo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alissondev.nuinfo.entities.People;

@RestController
@RequestMapping("/peoples")
public class PeopleController {

	@GetMapping
	public ResponseEntity<List<People>> test() {
		
		People p1 = new People();
		p1.setId(1L);
		p1.setName("Alisson");
		
		People p2 = new People();
		p2.setId(2L);
		p2.setName("Link");
				
		return ResponseEntity.ok(Arrays.asList(p1, p2));
	}
}
