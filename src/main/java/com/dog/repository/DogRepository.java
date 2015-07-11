package com.dog.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dog.controller.CreateDogs;

public interface DogRepository extends GraphRepository<CreateDogs>{
	
	@Query(value="start n=node({Id}) return n;")
	CreateDogs findById(@Param("Id") Long Id);
	
	@Query(value="match(n:CreateDogs) return n;")
	List<CreateDogs> getAll();
}
