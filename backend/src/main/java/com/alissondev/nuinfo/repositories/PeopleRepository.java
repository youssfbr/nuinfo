package com.alissondev.nuinfo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alissondev.nuinfo.entities.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

}
