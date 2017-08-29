package com.liberymutual.goforcode.wip.api;

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

import com.liberymutual.goforcode.wip.models.Movie;
import com.liberymutual.goforcode.wip.repositories.MovieRepository;

@RequestMapping("/api/movies")
@RestController
public class MovieApiController {

	private MovieRepository movieRepo;
	
	public MovieApiController(MovieRepository movieRepo) {
		this.movieRepo = movieRepo;
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
	
	
	@PutMapping("{id}")
	public Movie update(@RequestBody Movie movie, @PathVariable long id) {
		movie.setId(id);
		return movieRepo.save(movie);
	}
}