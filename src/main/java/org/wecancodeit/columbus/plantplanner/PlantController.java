package org.wecancodeit.columbus.plantplanner;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlantController {

	@Resource
	private PlantRepository plantRepo;

	@RequestMapping("/plants")
	public Iterable<Plant> findPlants() {
		return plantRepo.findAll();
	}

	@RequestMapping("/plants/{id}")
	public Plant findPlant(@PathVariable(name = "id") long id) {
		if (plantRepo.findOne(id) == null) {
			throw new SomethingNotFoundException();
		}
		return plantRepo.findOne(id);
	}
	
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public class SomethingNotFoundException extends RuntimeException {
		
	}
}

