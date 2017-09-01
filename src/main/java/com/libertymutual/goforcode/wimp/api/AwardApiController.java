package com.libertymutual.goforcode.wimp.api;

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

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

import io.swagger.annotations.Api;

@RequestMapping("/api/awards")
@Api(description = "Create, get, update, and delete awards.")
@RestController
public class AwardApiController {

	private AwardRepository awardRepo;
	
	public AwardApiController(AwardRepository awardRepo) {
		this.awardRepo = awardRepo;
	}


	@GetMapping("")
	public List<Award> getAll() {
		return awardRepo.findAll();
	}

	
	@GetMapping("{id}")
	public Award getOne(@PathVariable long id) throws StuffNotFoundException {
		Award award = awardRepo.findOne(id);
		if (award == null) {
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
		return award;
	}
	
	@DeleteMapping("{id}") 
	public String delete(@PathVariable long id) {
		try {
			Award award = awardRepo.findOne(id);
			String msg = award.getTitle() + " has been deleted.";
			awardRepo.delete(id);
			return msg;
		}
		catch (EmptyResultDataAccessException ardae) {
			return "This award does not exist";
		}
	}
	
	@PostMapping("")
	public Award create(@RequestBody Award award) {
		return awardRepo.save(award);
	}
	
	@PutMapping("{id}")
	public Award update(@RequestBody Award award, @PathVariable long id) {
		award.setId(id);
		return awardRepo.save(award);
	}
}