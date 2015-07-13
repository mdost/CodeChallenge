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
	public String getAllDogs(Model model, HttpServletResponse response){
		List<CreateDogs> getDogs = repo.getAll();
		
		if(getDogs.size() != 0){
			for(CreateDogs i : getDogs){
				System.out.println(i.getName()+" "+i.getId());
			}
			response.setStatus(HttpServletResponse.SC_OK);
		}else{
			System.out.println("its null!!!!!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		model.addAttribute("message", "why aren't you printing");
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
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String displayMap(ModelMap model, HttpServletResponse response){
		List<CreateDogs> getDogs = repo.getAll();
		
		if(getDogs.size() != 0){
			for(CreateDogs i : getDogs){
				System.out.println(i.getName()+" "+i.getId());
			}
			response.setStatus(HttpServletResponse.SC_OK);
		}
		model.addAttribute("listOfDogs", getDogs);

		
		return "map";
	}
	
	@RequestMapping(value = "dogClusters", method = RequestMethod.GET)
	public String kMeanClustering(@RequestParam("k") int clusters, ModelMap model, HttpServletResponse response){
		List<CreateDogs> getDogs = repo.getAll();
		
		if(getDogs.size() != 0){
			int length= getDogs.size();
			int minValue= getDogs.get(0).getWeight();
			int maxValue = getDogs.get(0).getWeight();
			int[][] clusterArray = new int[clusters][length];
			for(int q=0; q<clusters; q++){
				for(int y=0; y< length; y++){
					clusterArray[q][y]=-1;
					System.out.println(clusterArray[q][y]);
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
			System.out.println(minValue);
			System.out.println(maxValue);
			
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
				
				double v = Math.pow(i.getWeight()-k[0], 2);
				center=  Math.sqrt(v);
//				System.out.println("first_center: "+center+" weight: "+i.getWeight());
				int flag =0;
				
				for(int n=1; n< k.length;n++){
					double value = Math.pow(i.getWeight()-k[n], 2);
					center2=  Math.sqrt(value);
					
//					System.out.println("center2: "+center2);
					if(center2 < center){
//						System.out.println("center1: "+center);

						center= center2;
						flag=n;
//						System.out.println("final_center1: "+center);

					}
				}
				
				System.out.println(flag+" centerPt= "+center);
				for(int o=0; o<k.length; o++){
		        	 if(flag == o){
		        		 for(int b=0; b< length-1;b++){
		        			 if(clusterArray[o][b] == -1){
		        				 clusterArray[o][b]= i.getId().intValue();
		        				 System.out.println(o+"b: "+b+" value= "+clusterArray[o][b]);
		        				 break;
		        			 }
		        		 }
		        	 }
		         }
				
				System.out.println("------------------end-------------");
			}
			
			//
		      System.out.println("The real cluster array---------------------");
		     
		      for(int h=0; h<clusters; h++){
		    	  for(int v=0; v<length; v++){
		    		  System.out.println(clusterArray[h][v]);
		    	  }
		      }
		      
			model.addAttribute("clusteredPts", clusterArray);
			response.setStatus(HttpServletResponse.SC_OK);
		}
		
		return "algorithm";
	}
	
}
