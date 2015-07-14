package com.dog.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.dog.repository.DogRepository;

/**
 * @author mariamdost
 * 
 * Controller for the CreateDogs class
 * This controller contains many methods and performs multiple operation on the data stored in the database (Neo4j)
 *
 */
@Controller
public class CreateDogController {

	//declare a repository
	@Autowired
	DogRepository repo;
	
	/*
	 * Create an object of type CreateDogs and store it in the database
	 * return a response status of 200-created
	 */
	@RequestMapping(value = "dogCreated", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String createDog(@ModelAttribute CreateDogs dog, BindingResult result, Model model){
		model.addAttribute("dog", dog);
		model.addAttribute("message", dog.getName()+" has been entered into the system!");
		repo.save(dog);
		
		return "createDog";
	}
	
	/*
	 * Get all dogs from the database and set it to a specific ID that will be called in the view
	 */
	@RequestMapping(value ="dogs", method = RequestMethod.GET)
	public String getAllDogs(Model model, HttpServletResponse response){
		//get the # of objects in the database.
		int length = repo.size();
		
		//Validate that the database is not empty, if so return error- else proceed.
		if(length !=0){
			List<CreateDogs> getDogs = repo.getAll();
			model.addAttribute("listOfDogs", getDogs);
			
			response.setStatus(HttpServletResponse.SC_OK);
		}else{
			model.addAttribute("error", "At this time there are no registered dogs. Please register a dog!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		//render the view
		return "displayInfo";
		
	}
	
	/*
	 * Find dog that matches the specific ID passed in the URL
	 * return the displayInfo view
	 */
	@RequestMapping(value ="dogs/{id}", method = RequestMethod.GET)
	public String getDogId(@PathVariable("id") Long value, Model model){
		//check if the ID exists in the system
		boolean flag = repo.exists(value);;
		
		//if it does not exist then return with error message and the entire list of dogs in the system.
		if(flag == false){
			model.addAttribute("error", "invalid id, ID # does not exist in the system!");
			
			//check database contains elements, if not then just return with no list
			int length = repo.size();
			if(length !=0){
				List<CreateDogs> getDogs = repo.getAll();
				model.addAttribute("listOfDogs", getDogs);
			}

		}else{
			CreateDogs dog = repo.findById(value);
			model.addAttribute("dogId", dog);
		}

		return "displayInfo";
	}
	
	/*
	 * Get all dogs from the database, return the map view (render)
	 */
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String displayMap(ModelMap model, HttpServletResponse response){
		int length = repo.size();
		
		if(length !=0){
			List<CreateDogs> getDogs = repo.getAll();
			model.addAttribute("listOfDogs", getDogs);
		}else{
			model.addAttribute("error", "invalid id, ID # does not exist in the system!");
		}

		return "map";
	}
	
	/*
	 * Get dog by ID and render the view for map
	 */
	@RequestMapping(value ="MapById", method = RequestMethod.POST)
	public String getMapByDogID(@RequestParam("id") Long value, Model model){
		//check if the ID exists in the system
		boolean flag = repo.exists(value);
		
		//if it does not exist then return with error message and the entire list of dogs in the system.
		if(flag == false){
			model.addAttribute("error", "invalid id, ID # does not exist in the system!");
					
			//check database contains elements, if not then just return with no list
			int length = repo.size();
			if(length !=0){
				List<CreateDogs> getDogs = repo.getAll();
				model.addAttribute("listOfDogs", getDogs);
			}

		}else{
			CreateDogs dog = repo.findById(value);
			model.addAttribute("filterDogLocation", dog);
		}
		
		return "map";
	}
	
	/*
	 * Delete object (dog) with ID that matches the user input ID # from the database.
	 * return the displayInfo view (render)
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") Long value, ModelMap model, HttpServletResponse response){
		//check if the ID exists in the system
		boolean flag = repo.exists(value);
		
		if(flag== false){
			model.addAttribute("error", "invalid id, ID # does not exist in the system!");
		}else{
			repo.deleteByID(value);
		
			int length = repo.size();
			if(length !=0){
				List<CreateDogs> getDogs = repo.getAll();
				model.addAttribute("listOfDogs", getDogs);
			}
			
			model.addAttribute("deletedDog", "Dog has been deleted!");
		}
		
		return "displayInfo";
	}
	
	/*
	 * Render the editInfo page, as well as find dog that matches user input ID and pass it to the editInfo view
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("dogID") Long id, ModelMap model, HttpServletResponse response){
		CreateDogs dog= repo.findById(id);
		model.addAttribute("updateDog", dog);
		return "editInfo";
	}
	
	/*
	 * Update the dog object that matches the ID # the user has provided
	 * return displayInfo view (render)
	 */
	@RequestMapping(value = "/dogEdited", method = RequestMethod.GET)
	public String editDog(@RequestParam("id") Long id, @ModelAttribute CreateDogs updatedDog,ModelMap model, HttpServletResponse response){				
		boolean flag = repo.exists(id);
		
		if(flag== false){
			model.addAttribute("error", "invalid id, ID # does not exist in the system!");
		}else{
			CreateDogs newDog= repo.updateByID(id, updatedDog.getWeight(), updatedDog.getTemperature(),updatedDog.getHeartbeat(),updatedDog.getName(), updatedDog.getLat(),updatedDog.getLong());
		
			//get all dogs from the database, so the user can see the updated change
			int length = repo.size();
			if(length !=0){
				List<CreateDogs> getDogs = repo.getAll();
				model.addAttribute("listOfDogs", getDogs);
			}
			
			model.addAttribute("updatedDog", newDog);
		}
		
		return "displayInfo";
	}
	
	/*
	 * Performs the k-mean clustering algorithm for dogs weight on the dataset that is available in the system.
	 * return the algorithm view (render)
	 */
	@RequestMapping(value = "dogClusters", method = RequestMethod.GET)
	public String kMeanClustering(@RequestParam("k") int clusters, ModelMap model, HttpServletResponse response){
		int dbLength = repo.size();
		
		if(dbLength ==0){
			model.addAttribute("error", "Database is empty, please register a dog in order to perform k-means clustering algorithm.");
			return "algorithm";
		}
		
		//get all dogs from the database
		List<CreateDogs> getDogs = repo.getAll();
		
		int length= getDogs.size();		//get # of element
		int minValue= getDogs.get(0).getWeight();		//initially set the minValue for weight
		int maxValue = getDogs.get(0).getWeight();		//initially set the maxValue for weight
		
		//initialize cluster array to -1
		CreateDogs[][] clusterArray = new CreateDogs[clusters][length];
		for(int q=0; q<clusters; q++){
			for(int y=0; y< length; y++){
				clusterArray[q][y]=null;
			}
		}
		
		//choose k initial cluster centers
		//find min and max value in the list of dogs
		for(CreateDogs i : getDogs){				
			if(i.getWeight() < minValue){
				minValue= i.getWeight();
			}
				
			if(i.getWeight() > maxValue){
				maxValue= i.getWeight();
			}
		}
			
		//generate k random cluster centers in the range of the list
		int[] k = new int[clusters];
		Random rand = new Random();
			
		for(int i=0; i<clusters; i++){
			k[i] = rand.nextInt((maxValue-minValue)+1);
			System.out.println(k[i]);
		}
			
		//for each point assign it to the cluster with the nearest distance
		double center, center2;
			
		for(CreateDogs i: getDogs){
			//calculate the distance between the centroid (center) point choosen and each point.
			double v = Math.pow(i.getWeight()-k[0], 2);
			center=  Math.sqrt(v);
			int flag =0;	//initially flag set to cluster 0
				
			//check which centroid does the point in the dataset has closest distance.
			for(int n=1; n< k.length;n++){
				double value = Math.pow(i.getWeight()-k[n], 2);
				center2=  Math.sqrt(value);
				
				if(center2 < center){
					center= center2;
					flag=n;		//change flag if the point is closer to another cluster

				}
			}
			
			//each row in the array corresponds to a cluser, for example row 0 is cluster 0, row 1 is cluster 1, etc.
			//place each object in correct row according to its flag.
			for(int o=0; o<k.length; o++){
		        if(flag == o){
		        	for(int b=0; b< length;b++){
		        		if(clusterArray[o][b] == null){
		        			clusterArray[o][b]= i;
		        			break;
		        		}
		        	}
		        }
		    }
				
		}
				     
//		for(int h=0; h<clusters; h++){
//		   for(int v=0; v<length; v++){
//			   if(clusterArray[h][v] != null)
//				   System.out.println(clusterArray[h][v].getId());
//		    }
//		}
		      
		model.addAttribute("clusteredPts", clusterArray);
		response.setStatus(HttpServletResponse.SC_OK);

		return "algorithm";
	}
	
}
