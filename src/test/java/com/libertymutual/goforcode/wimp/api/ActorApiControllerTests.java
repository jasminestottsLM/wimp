package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.api.ActorApiController;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;

public class ActorApiControllerTests {
 
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	private ActorApiController controller;
	 
	@Before  
	public void setUp() {
		actorRepo = mock(ActorRepository.class);
		awardRepo = mock(AwardRepository.class);
		controller = new ActorApiController(actorRepo, awardRepo);
	} 
	   
	@Test 
	public void test_getAll_returns_all_actors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		when(actorRepo.findAll()).thenReturn(actors);
		
		List<Actor> actualActors = controller.getAll();
		
		assertThat(actualActors.size()).isEqualTo(2);
		assertThat(actualActors.get(0)).isSameAs(actors.get(0));
		assertThat(actualActors.get(1)).isSameAs(actors.get(1));
		verify(actorRepo).findAll();
	}
	
	@Test 
	public void test_getOne_returns_one() throws StuffNotFoundException {
		Actor testActor = new Actor();
		when(actorRepo.findOne(5l)).thenReturn(testActor);
		
		Actor actual = controller.getOne(5l);
		
		assertThat(actual).isSameAs(testActor);
		verify(actorRepo).findOne(5l);
	}
	
	@Test
	public void test_getOne_is_null() {
		try {
			controller.getOne(1);
			fail("The Controller did not throw the StuffNotFoundException.");
		} catch(StuffNotFoundException snfe) {
			
		}
	}
	
	@Test 
	public void test_delete_actor() {
		Actor actor = new Actor();
		actor.setFirstName("first");
		actor.setLastName("last");
		when(actorRepo.findOne(4l)).thenReturn(actor);
		
		String msgOfLoveliness = controller.delete(4l);
		
		verify(actorRepo).delete(4l);
		verify(actorRepo).findOne(4l);
		assertThat(msgOfLoveliness).isEqualTo(actor.getFirstName() + " " + actor.getLastName() + " has been deleted.");
	}

	
	@Test
	public void test_null_throws_ERDA() throws StuffNotFoundException {
		when(actorRepo.findOne(4l)).thenThrow(new EmptyResultDataAccessException(0));
		
		String msg = controller.delete(4l);
		
		assertThat(msg).isSameAs("This actor does not exist");
		verify(actorRepo).findOne(4l);
	}

	@Test
	public void test_create_creates_actor() {
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		Actor actualActor = controller.create(actor);
		
		verify(actorRepo).save(actor);
		assertThat(controller.create(actor)).isSameAs(actualActor);
	}
	
	@Test
	public void test_that_update_updates_actor() {
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		Actor actualActor = controller.update(actor, 1);
		
		verify(actorRepo).save(actor);
		assertThat(controller.update(actor, 1)).isSameAs(actualActor);
	}
	
	@Test
	public void associateAward_associates_award_with_actor() {
		Actor actor = new Actor();
		Award award = new Award();
		when(actorRepo.findOne(4l)).thenReturn(actor);
		when(awardRepo.findOne(4l)).thenReturn(award);
		
		Actor actualActor = controller.associateAward(4l, award);
		
		verify(actorRepo).findOne(4l);
		assertThat(actor).isSameAs(actualActor);
	}
}
