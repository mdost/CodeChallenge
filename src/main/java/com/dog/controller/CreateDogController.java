package com.dog.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dog.repository.DogRepository;

/**
 * 
 * @author mariamdost
 * Controller that controls the dog class.
 *
 */
@Controller
public class CreateDogController {

	@Autowired
	DogRepository repo;
	
	@RequestMapping(value = "dogCreated", method = RequestMethod.POST)
	public String createDog(@ModelAttribute CreateDogs dog, BindingResult result, Model model){
		CreateDogs dogs = new CreateDogs();
		
		dogs.setName(dog.getName());
		dogs.setTemperature(dog.getTemperature());
		dogs.setWeight(dog.getWeight());
		dogs.setHeartbeat(dog.getHeartbeat());
		dogs.setLat(dog.getLat());
		dogs.setLon(dog.getLong());
		System.out.println(dog.getLong());
		
		model.addAttribute("dog", dogs);
		model.addAttribute("message", dog.getName()+" has been entered into the system!");
		repo.save(dogs);
		
		return "createDog";
	}
	
	@RequestMapping(value ="dogs", method = RequestMethod.GET)
	public String getAllDogs(Model model){
		List<CreateDogs> getDogs = repo.getAll();
		
		if(getDogs.size() != 0){
			for(CreateDogs i : getDogs){
				System.out.println(i.getName()+" "+i.getId());
			}
		}else{
			System.out.println("its null!!!!!");
		}
		model.addAttribute("listOfDogs", getDogs);
		
		return "displayInfo";
		
	}
	
	@RequestMapping(value ="dogs", method = RequestMethod.POST)
	public String getDogId(@RequestParam("id") Long value, Model model){
		System.out.println(value);
		CreateDogs dog = repo.findById(value);
		System.out.println(dog.getName());
		model.addAttribute("dogId", dog);
		return "displayInfo";
	}
	
}
