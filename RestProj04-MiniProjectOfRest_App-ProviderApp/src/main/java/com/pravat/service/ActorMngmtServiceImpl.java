package com.pravat.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pravat.exception.ActorNotFoundException;
import com.pravat.model.Actor;
import com.pravat.repo.IActorRepo;

@Service("actorMngmtService")
public class ActorMngmtServiceImpl implements IActorMngmtService {

	@Autowired
	private IActorRepo actorRepo;

	@Override
	public String registerActor(Actor actor) {
		Actor actors = actorRepo.save(actor);
		return "Actor is registered with id value ::" + actors.getActorid();
	}

	/*@Override
	public Iterable<Actor> getAllActors() {
		Iterable<Actor> it = actorRepo.findAll();
		List<Actor> list1 = (List<Actor>) it;
		list1.sort((t1, t2) -> t1.getActorName().compareTo(t2.getActorName()));
		return list1;
	}*/

	/*@Override
	public Iterable<Actor> getAllActors() {
		Iterable<Actor> it = actorRepo.findAll();
		List<Actor> list1 = StreamSupport.stream(it.spliterator(), false)
				.sorted((t1, t2) -> t1.getActorName().compareTo(t2.getActorName())).collect(Collectors.toList());
			return list1;
	}*/
	@Override
	public Iterable<Actor> getAllActors() {
		Iterable<Actor> it = actorRepo.findAll();
		Collections.sort((List<Actor>) it, (t1, t2) -> t1.getActorName().compareTo(t2.getActorName()));
		return it;
	}

	@Override
	public Actor getActorById(Integer id) {
		// Actor actor= actorRepo.findById(id).get();
		// return actor;
		return actorRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid actor id"));
	}

	@Override
	public List<Actor> fetchActorsByCategory(String category1, String category2) {
		List<Actor> list = actorRepo.getActorsByCategories(category1, category2);
		return list;
	}

	@Override
	public String updateActor(Actor actor) {
		Optional<Actor> opt = actorRepo.findById(actor.getActorid());
		if (opt.isPresent()) {
			actorRepo.save(actor); // update object
			return "Actor Information is updated";
		} else {
			throw new ActorNotFoundException("Actor not found");
		}

	}

	@Override
	public String deleteActorById(Integer id) {
		Optional<Actor> opt = actorRepo.findById(id);
		if (opt.isPresent()) {
			actorRepo.deleteById(id);
			return "Actor information is successfully deleted";
		} else {
			throw new ActorNotFoundException("Actor not found");
		}

	}

	@Override
	public String updateActorMobileNo(int id, long newMobileNo) {
		Optional<Actor> opt = actorRepo.findById(id);
		if (opt.isPresent()) {
			Actor actor = opt.get();
			actor.setMobileNo(newMobileNo);
			actorRepo.save(actor); // update object
			return "Actor's Mobile Number Is Updated";

		} else {
			throw new ActorNotFoundException("Actor not found");
		}
		
	}

}
