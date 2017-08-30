package com.liberymutual.goforcode.wimp.api;

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

import com.liberymutual.goforcode.wimp.models.Actor;
import com.liberymutual.goforcode.wimp.models.ActorWithMovies;
import com.liberymutual.goforcode.wimp.models.Movie;
import com.liberymutual.goforcode.wimp.repositories.ActorRepository;
import com.liberymutual.goforcode.wimp.repositories.MovieRepository;

@RequestMapping("/api/movies")
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