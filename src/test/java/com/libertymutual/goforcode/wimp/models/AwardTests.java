package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;




public class AwardTests { 
/*	
	private AwardRepository awardRepo;
	private ActorRepository actorRepo;
	
	@Before
	public void setUp() {
		awardRepo = mock(AwardRepository.class);
		actorRepo = mock(ActorRepository.class);
	}
 */
	@Test
	public void test_all_gets_and_sets() {
		new BeanTester().testBean(Award.class);
	} 
	 
	@Test
	public void test_Award_constructor_makes_award() {
		Award newAward = new Award("title", "organization",  1999);
	
		assertThat(newAward.getTitle()).isSameAs("title");
		assertThat(newAward.getOrganization()).isSameAs("organization");
		assertThat(newAward.getYear()).isEqualTo(1999);
	}
/*	
	@Test 
	public void test_get_and_set_Organization() { 
		Award newAward = new Award();
		newAward.setOrganization("test");
		
		assertThat(newAward.getOrganization()).isSameAs("test");
	}  
	   
	@Test 
	public void test_get_and_set_Year() { 
		 Award newAward = new Award();
		 newAward.setYear(1999);
		 
		 assertThat(newAward.getYear()).isEqualTo(1999);
	} 
	   
	@Test  
	public void test_get_and_set_Actor() { 
		Actor actor = new Actor();
		Award newAward = new Award();
		when(actorRepo.findOne(4l)).thenReturn(actor);
		
		newAward.setActor(actor);
		
		
		assertThat(newAward.getActor()).isSameAs(actor);
	}
	*/
}