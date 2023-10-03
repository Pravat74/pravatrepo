package com.pravat.service;

import java.util.List;

import com.pravat.model.Actor;

public interface IActorMngmtService {

	public String registerActor(Actor actor);

	public Iterable<Actor> getAllActors();

	public Actor getActorById(Integer id);

	public List<Actor> fetchActorsByCategory(String category1, String category2);
	
	public String updateActor(Actor actor);
	
	public String deleteActorById(Integer id);
	
	public String updateActorMobileNo(int id, long newMobileNo);
}
