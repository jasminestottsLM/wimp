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

import com.libertymutual.goforcode.wimp.repositories.AwardRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository; 

public class ActorTests { 

	
	@Test
	public void test_all_gets_and_sets() {
		new BeanTester().testBean(Actor.class);
	} 
	/*
	private AwardRepository awardRepo;
	private MovieRepository movieRepo;
	
	@Before
	public void setUp() {
		movieRepo = mock(MovieRepository.class);
		awardRepo = mock(AwardRepository.class);
	}
	
	@Test 
	public void test_setLastName() {  
		Actor newActor = new Actor();
		newActor.setLastName("test");
		
		assertThat(newActor.getLastName()).isSameAs("test");
	}
	   
	@Test 
	public void test_get_and_set_Active_Since_Year() { 
		Actor newActor = new Actor();
		newActor.setActiveSinceYear(1l);
		
		assertThat(newActor.getActiveSinceYear()).isSameAs(1l);
	}
	 
 
	@Test 
	public void test_get_and_set_Birth_Date() { 
		Actor newActor = new Actor();
		Date date = new Date();
		newActor.setBirthDate(date); 
		
		assertThat(newActor.getBirthDate()).isSameAs(date);
	}
	
	@Test  
	public void test_get_and_set_Movies() { 
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		when(movieRepo.findAll()).thenReturn(movies); 
		
		Actor newActor = new Actor();
		newActor.setMovies(movies);
		
		assertThat(newActor.getMovies()).isSameAs(movies);
	}
	
	@Test 
	public void test_get_and_set_Awards() { 
		ArrayList<Award> awards = new ArrayList<Award>();
		awards.add(new Award());
		when(awardRepo.findAll()).thenReturn(awards);
		
		Actor newActor = new Actor();
		newActor.setAwards(awards);
		
		assertThat(newActor.getAwards()).isSameAs(awards);
	}
	*/
} 