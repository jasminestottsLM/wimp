package com.liberymutual.goforcode.wimp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liberymutual.goforcode.wimp.models.Actor;
import com.liberymutual.goforcode.wimp.models.Movie;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>{

//	List<Actor> findbyMovieIdEquals(Movie movieId);
}
