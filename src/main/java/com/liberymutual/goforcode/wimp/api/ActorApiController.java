package com.liberymutual.goforcode.wimp.api;

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

import com.liberymutual.goforcode.wimp.models.Actor;
import com.liberymutual.goforcode.wimp.models.ActorWithMovies;
import com.liberymutual.goforcode.wimp.models.Award;
import com.liberymutual.goforcode.wimp.models.Movie;
import com.liberymutual.goforcode.wimp.repositories.ActorRepository;
import com.liberymutual.goforcode.wimp.repositories.AwardRepository;
import com.liberymutual.goforcode.wimp.repositories.MovieRepository;

@RequestMapping("/api/actors")
@RestController
public class ActorApiController {

	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	
	public ActorApiController(ActorRepository actorRepo, AwardRepository awardRepo) {
		this.actorRepo = actorRepo;
		this.awardRepo = awardRepo;
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
/*		ActorWithMovies newActor = new ActorWithMovies();
		newActor.setActiveSinceYear(actor.getActiveSinceYear());
		newActor.setBirthDate(actor.getBirthDate());
		newActor.setMovies(actor.getMovies());
		newActor.setFirstName(actor.getFirstName());
		newActor.setLastname(actor.getLastName());
		return newActor;
*/		
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
/*	
	@PostMapping("{actorId}/awards") 
	public Actor associateAward(@PathVariable long actorId, @RequestBody Award award) {
		Actor actor = actorRepo.findOne(actorId);
		award = awardRepo.findOne(award.getId());
		actor.addAward(award);
		actorRepo.save(actor);
		return actor;
	}
*/	
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable long id) {
		actor.setId(id);
		return actorRepo.save(actor);
	}
}