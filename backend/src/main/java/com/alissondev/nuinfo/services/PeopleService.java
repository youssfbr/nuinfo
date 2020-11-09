package com.alissondev.nuinfo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alissondev.nuinfo.entities.People;
import com.alissondev.nuinfo.repositories.PeopleRepository;
import com.alissondev.nuinfo.services.exceptions.DatabaseException;
import com.alissondev.nuinfo.services.exceptions.NotFoundException;

@Service
public class PeopleService {

	@Autowired
	private PeopleRepository repository;
	
	@Transactional(readOnly = true)
	public List<People> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public People findById(Long id) {
		Optional<People> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NotFoundException("Id " + id + " not found"));		
	}
	
	@Transactional
	public People insert(People people) {
		return repository.save(people);		 
	}

	@Transactional
	public People update(Long id, People people) {
		try {
			People entity = repository.getOne(id);
			if (people.getName() != null) entity.setName(people.getName());
			if (people.getCpf() != null) entity.setCpf(people.getCpf());
			if (people.getDateBirth() != null) entity.setDateBirth(people.getDateBirth());
			if (people.getEmail() != null) entity.setEmail(people.getEmail());
			
			entity = repository.save(entity);
			return entity;	
		}		
		catch (EntityNotFoundException e) {
			throw new NotFoundException("Id " + id + " not found");
		}		
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);	
		} 
		catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("Id " + id + " not found");
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}		
	}
}
