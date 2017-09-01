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
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;

public class MovieApiControllerTests {

	private MovieRepository movieRepo;
	private ActorRepository actorRepo; 
	private MovieApiController controller;
	
	@Before
	public void setUp() {
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
		controller = new MovieApiController(movieRepo, actorRepo);

	} 
	
	@Test
	public void test_getAll_returns_all_movies() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		when(movieRepo.findAll()).thenReturn(movies);
		// I hate parentheses in testing.
		
		List<Movie> actualMovies = controller.getAll();
		
		assertThat(actualMovies.size()).isEqualTo(2);
		assertThat(actualMovies.get(0)).isSameAs(movies.get(0));
		assertThat(actualMovies.get(1)).isSameAs(movies.get(1));
		verify(movieRepo).findAll();
	}
	
	@Test 
	public void test_getOne_returns_one() throws StuffNotFoundException {
		Movie testMovie = new Movie();
		when(movieRepo.findOne(5l)).thenReturn(testMovie);
		
		Movie actual = controller.getOne(5l);
		
		assertThat(actual).isSameAs(testMovie);
		verify(movieRepo).findOne(5l);
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
	public void test_delete_movie() {
		Movie movie = new Movie();
		movie.setTitle("test");
		when(movieRepo.findOne(4l)).thenReturn(movie);
		
		String msgOfLoveliness = controller.delete(4l);
		
		verify(movieRepo).delete(4l);
		verify(movieRepo).findOne(4l);
		assertThat(msgOfLoveliness).isEqualTo(movie.getTitle() + " has been deleted.");
	}

	
	@Test
	public void test_null_throws_ERDA() throws StuffNotFoundException {
		when(movieRepo.findOne(4l)).thenThrow(new EmptyResultDataAccessException(0));
		
		String msg = controller.delete(4l);
		
		assertThat(msg).isSameAs("This movie does not exist");
		verify(movieRepo).findOne(4l);
	}

	@Test
	public void test_create_creates_movie() {
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		
		Movie actualMovie = controller.create(movie);
		
		verify(movieRepo).save(movie);
		assertThat(controller.create(movie)).isSameAs(actualMovie);
	}
	
	@Test
	public void test_that_update_updates_movie() {
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		
		Movie actualMovie = controller.update(movie, 1);
		
		verify(movieRepo).save(movie);
		assertThat(controller.update(movie, 1)).isSameAs(actualMovie);
	}
	
	@Test
	public void associateActor_associates_actor_with_movie() {
		Movie movie = new Movie();
		Actor actor = new Actor();
		when(movieRepo.findOne(4l)).thenReturn(movie);
		when(actorRepo.findOne(4l)).thenReturn(actor);
		
		Movie actualMovie = controller.associateActor(4l, actor);
		
		verify(movieRepo).findOne(4l);
		assertThat(controller.associateActor(4l, actor)).isSameAs(actualMovie);
	}

}
