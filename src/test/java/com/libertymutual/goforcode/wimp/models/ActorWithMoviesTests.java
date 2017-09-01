package com.libertymutual.goforcode.wimp.models;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;

public class ActorWithMoviesTests {

	@Test
	public void test_Actor_with_Movies_returns_movies() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		 
		ActorWithMovies thing = new ActorWithMovies();
		thing.setMovies(movies);
		
		assertThat(thing.noReallyMovies()).isSameAs(movies);
	}
}
