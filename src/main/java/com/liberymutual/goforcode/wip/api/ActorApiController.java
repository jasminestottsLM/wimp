package com.liberymutual.goforcode.wip.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liberymutual.goforcode.wip.models.Actor;
import com.liberymutual.goforcode.wip.models.Movie;
import com.liberymutual.goforcode.wip.repositories.ActorRepository;
import com.liberymutual.goforcode.wip.repositories.MovieRepository;

@RequestMapping("/api/actors")
@RestController
public class ActorApiController {

private ActorRepository actorRepo;
	
	public ActorApiController(ActorRepository actorRepo) {
		this.actorRepo = actorRepo;
	}


	@GetMapping("")
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}

	
	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) throws StuffNotFoundException {
		Actor actor = actorRepo.findOne(id);
		if (actor == null) {
			throw new StuffNotFoundException();
		}
		return actor;
	}
	
	@DeleteMapping("{id}") 
	public String delete(@PathVariable long id) {
		try {
			Actor actor = actorRepo.findOne(id);
			String msg = actor.getFirstName() + " " + actor.getLastName() + " has been deleted.";
			actorRepo.delete(id);
			return msg;
		}
		catch (EmptyResultDataAccessException ardae) {
			return "This actor does not exist";
		}
	}
	
	@PostMapping("")
	public Actor create(@RequestBody Actor actor) {
		return actorRepo.save(actor);
	}
	
	
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable long id) {
		actor.setId(id);
		return actorRepo.save(actor);
	}
}