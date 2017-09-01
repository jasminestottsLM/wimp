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
import com.libertymutual.goforcode.wimp.repositories.MovieRepository; 

public class MovieTests {

	@Test
	public void test_all_gets_and_sets() {
		new BeanTester().testBean(Movie.class);
	} 
	
	
/*	
	
	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	
	@Before 
	public void setUp() {
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
	}
	
	@Test 
	public void test_get_and_set_Id() { 
		Movie newMovie = new Movie();
		newMovie.setId(3l); 
		
		assertThat(newMovie.getId()).isSameAs(3l);
	}
	  
	@Test 
	public void test_get_and_set_ReleaseDate() { 
		Movie newMovie = new Movie();
		Date date = new Date();
		newMovie.setReleaseDate(date); 
		
		assertThat(newMovie.getReleaseDate()).isSameAs(date);
	}
	
	@Test
	public void test_get_and_set_Budget() { 
		Movie newMovie = new Movie();
		newMovie.setBudget(4l);  
		
		assertThat(newMovie.getBudget()).isSameAs(4l);
	}
	
	@Test
	public void test_get_and_set_Distributor() { 
		Movie newMovie = new Movie();
		newMovie.setDistributor("test"); 
		
		assertThat(newMovie.getDistributor()).isSameAs("test");
	}
	
	@Test
	public void test_get_and_set_Actors() { 
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		Movie newMovie = new Movie();
		
		newMovie.setActors(actors); 
		
		assertThat(newMovie.getActors()).isSameAs(actors);
	}
	*/
}