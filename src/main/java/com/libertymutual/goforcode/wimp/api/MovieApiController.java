package com.libertymutual.goforcode.wimp.api;

import java.util.List;

import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/movies")
@Api(description = "Create, get, update, and delete movies.  Also adds actors to movies.")
@RestController
public class MovieApiController {

	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	
	public MovieApiController(MovieRepository movieRepo, ActorRepository actorRepo) {
		this.movieRepo = movieRepo;
		this.actorRepo = actorRepo;
	}


	@GetMapping("")
	public List<Movie> getAll() {
		return movieRepo.findAll();
	}

	
	@GetMapping("{id}")
	public Movie getOne(@PathVariable long id) throws StuffNotFoundException {
		Movie movie = movieRepo.findOne(id);
		if (movie == null) {
			throw new StuffNotFoundException();
		}
		return movie;
	}
	
	@ApiOperation(value = "Delete movie using id", notes = "This will completely delete a movie!")
	@DeleteMapping("{id}") 
	public String delete(@PathVariable long id) {
		try {
			Movie movie = movieRepo.findOne(id);
			String msg = movie.getTitle() + " has been deleted.";
			movieRepo.delete(id);
			return msg;
		}
		catch (EmptyResultDataAccessException ardae) {
			return "This movie does not exist";
		}
	}
	
	@PostMapping("")
	public Movie create(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}
	
	@ApiOperation(value = "Add an actor to a movie")
	// if you use @ApiOperation(value = "${api.movie.associateactor}}"), you set {thing} in the application properties
	// add api.movie.associateActor
	@PostMapping("{movieId}/actors")
	public Movie associateActor(@PathVariable long movieId, @RequestBody Actor actor) {
		Movie movie = movieRepo.findOne(movieId);
		actor = actorRepo.findOne(actor.getId());
		movie.addActor(actor);
		movieRepo.save(movie);
		return movie;
	}
		
	@PutMapping("{id}")
	public Movie update(@RequestBody Movie movie, @PathVariable long id) {
		movie.setId(id);
		return movieRepo.save(movie);
	}
}