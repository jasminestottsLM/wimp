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

public class AwardApiControllerTests {

	private AwardRepository awardRepo;
	private AwardApiController controller;
	
	@Before
	public void setUp() {
		awardRepo = mock(AwardRepository.class);
		controller = new AwardApiController(awardRepo);
	} 
	
	@Test
	public void test_getAll_returns_all_awards() {
		ArrayList<Award> awards = new ArrayList<Award>();
		awards.add(new Award());
		awards.add(new Award());
		when(awardRepo.findAll()).thenReturn(awards);
		
		List<Award> actualAwards = controller.getAll();
		
		assertThat(actualAwards.size()).isEqualTo(2);
		assertThat(actualAwards.get(0)).isSameAs(awards.get(0));
		assertThat(actualAwards.get(1)).isSameAs(awards.get(1));
		verify(awardRepo).findAll();
	}
	
	@Test 
	public void test_getOne_returns_one() throws StuffNotFoundException {
		Award testAward = new Award();
		when(awardRepo.findOne(5l)).thenReturn(testAward);
		
		Award actual = controller.getOne(5l);
		
		assertThat(actual).isSameAs(testAward);
		verify(awardRepo).findOne(5l);
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
	public void test_delete_award() {
		Award award = new Award();
		award.setTitle("title");
		when(awardRepo.findOne(4l)).thenReturn(award);
		
		String msgOfLoveliness = controller.delete(4l);
		
		verify(awardRepo).delete(4l);
		verify(awardRepo).findOne(4l);
		assertThat(msgOfLoveliness).isEqualTo(award.getTitle() + " has been deleted.");
	}

	
	@Test
	public void test_null_throws_ERDA() throws StuffNotFoundException {
		when(awardRepo.findOne(4l)).thenThrow(new EmptyResultDataAccessException(0));
		
		String msg = controller.delete(4l);
		
		assertThat(msg).isSameAs("This award does not exist");
		verify(awardRepo).findOne(4l);
	}

	@Test
	public void test_create_creates_award() {
		Award award = new Award();
		when(awardRepo.save(award)).thenReturn(award);
		
		Award actualAward = controller.create(award);
		
		verify(awardRepo).save(award);
		assertThat(controller.create(award)).isSameAs(actualAward);
	}
	
	@Test
	public void test_that_update_updates_award() {
		Award award = new Award();
		when(awardRepo.save(award)).thenReturn(award);
		
		Award actualAward = controller.update(award, 1);
		
		verify(awardRepo).save(award);
		assertThat(controller.update(award, 1)).isSameAs(actualAward);
	}
	
	
}
