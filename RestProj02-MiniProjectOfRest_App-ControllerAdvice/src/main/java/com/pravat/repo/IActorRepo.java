package com.pravat.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pravat.model.Actor;

public interface IActorRepo extends JpaRepository<Actor, Integer> {
	@Query("from Actor where category in(:c1,:c2)order by category")  //JPQL Queries
	public List<Actor> getActorsByCategories(@Param("c1") String category1, @Param("c2") String category2);

}
