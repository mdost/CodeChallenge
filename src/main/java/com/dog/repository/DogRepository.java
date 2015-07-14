package com.dog.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dog.controller.CreateDogs;

/**
 * This repository uses Neo4j to store information about dogs.
 * This repository supports add dog, find dog by ID (search), get all dogs, delete a dog by ID and update dog by ID.
 */
public interface DogRepository extends GraphRepository<CreateDogs>{
	
	//check if database contains any entires, return the count or # of objects that are in the database.
	@Query(value="match(n:CreateDogs) return count(n);")
	int size();

	//search database and determine if the passed ID matches any ID stored in database, if so return that object
	@Query(value="start n=node({Id}) return n;")
	CreateDogs findById(@Param("Id") Long Id);
	
	//return all objects in the database
	@Query(value="match(n:CreateDogs) return n;")
	List<CreateDogs> getAll();
	
	//If ID matches a dog ID in the database, delete that dog.
	@Query(value="start n=node({Id}) delete n;")
	void deleteByID(@Param("Id") Long Id);
	
	//If ID matches a dog ID in the database, update all fields of that dog and return the updated dog.
	@Query(value="start n=node({Id}) set n.weight={weight}, n.name={name}, n.heartbeat={heartbeat}, n.temperature={temp}, n.lat={lat}, n.lon={lon} return n;")
	CreateDogs updateByID(@Param("Id") Long Id, @Param("weight") int weight, @Param("heartbeat") int heartbeat, @Param("temp") int temperature, @Param("name") String name, @Param("lat") double lat, @Param("lon") double lon);
}
