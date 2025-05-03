package com.flickfinder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieRatingTest {

	private MovieRating movieRating;
	
	@BeforeEach
	public void setUp() {
		movieRating = new MovieRating(1, "The Matrix", 7.4, 1234, 1999);
	}
	
	@Test
	public void testMovieRatingCreated() {
		assertEquals(1, movieRating.getId());
		assertEquals("The Matrix", movieRating.getTitle());
		assertEquals(1999, movieRating.getYear());
		
		assertEquals(7.4, movieRating.getRating());
		assertEquals(1234, movieRating.getVotes());
	}
	
	@Test
	public void testMovieRatingSetters() {
		movieRating.setId(2);
		movieRating.setTitle("The Matrix Reloaded");
		movieRating.setYear(2003);
		
		movieRating.setRating(5.7);
		movieRating.setVotes(947);
		
		assertEquals(2, movieRating.getId());
		assertEquals("The Matrix Reloaded", movieRating.getTitle());
		assertEquals(2003, movieRating.getYear());
		assertEquals(5.7, movieRating.getRating());
		assertEquals(947, movieRating.getVotes());
	}
}
