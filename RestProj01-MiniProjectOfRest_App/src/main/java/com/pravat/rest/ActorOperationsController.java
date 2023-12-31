package com.pravat.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pravat.model.Actor;
import com.pravat.service.IActorMngmtService;

@RestController
@RequestMapping("/actor/api")
public class ActorOperationsController {
	@Autowired
	private IActorMngmtService service;

	@PostMapping("/save")
	public ResponseEntity<String> saveActor(@RequestBody Actor actor) {

		try {
			// use service
			String msg = service.registerActor(actor);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/report")
	public ResponseEntity<?> fetchAllActors() {

		try {
			// use service
			Iterable<Actor> list = service.getAllActors();
			// return response entity object
			return new ResponseEntity<Iterable<Actor>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> fetchActorById(@PathVariable int id) {
		// use service
		try {
			Actor actor = service.getActorById(id);
			return new ResponseEntity<Actor>(actor, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/actorsinfo/{category1}/{category2}")
	public ResponseEntity<?> showActorsByCategory(@PathVariable String category1, @PathVariable String category2) {

		try {
			// use service
			List<Actor> list = service.fetchActorsByCategory(category1, category2);
			return new ResponseEntity<List<Actor>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/modify")
	public ResponseEntity<String> modifyActor(@RequestBody Actor actor) {
		try {
			// use service
			String msg = service.updateActor(actor);
			return new ResponseEntity<String>(msg, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteActor(@PathVariable("id") int id) {
		try {
			// use service
			String msg = service.deleteActorById(id);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/updateMobileNo/{id}/{newNo}")
	public ResponseEntity<String> changeActorMobileNo(@PathVariable("id") int id,
			@PathVariable("newNo") long newMobileNo) {
		try {
			// use service
			String msg = service.updateActorMobileNo(id, newMobileNo);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
