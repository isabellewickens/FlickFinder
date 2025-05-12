package com.flickfinder.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.model.MovieRating;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

/**
 * Test for the Movie Data Access Object. This uses an in-memory database for
 * testing purposes.
 */

class MovieDAOTest {

	/**
	 * The movie data access object.
	 */

	private MovieDAO movieDAO;

	/**
	 * Seeder
	 */

	Seeder seeder;

	/**
	 * Sets up the database connection and creates the tables. We are using an
	 * in-memory database for testing purposes. This gets passed to the Database
	 * class to get a connection to the database. As it's a singleton class, the
	 * entire application will use the same connection.
	 */
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		movieDAO = new MovieDAO();

	}

	/**
	 * Tests the getAllMovies method. We expect to get a list of all movies in the
	 * database. We have seeded the database with 6 movies, so we expect to get 6
	 * movies back. At this point, we avoid checking the actual content of the list.
	 */
	@Test
	void testGetAllMovies() {
		try {
			List<Movie> movies = movieDAO.getAllMovies();
			assertEquals(6, movies.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getAllMovies method when we want to limit the number of results
	 * returned. We expect to receive just 2 results.
	 */
	@Test
	void testGetAllMoviesWithLimit() {
		try {
			List<Movie> movies = movieDAO.getAllMovies(2);
			assertEquals(2, movies.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getMovieById method. We expect to get the movie with the specified
	 * id.
	 */
	@Test
	void testGetMovieById() {
		Movie movie;
		try {
			movie = movieDAO.getMovieById(1);
			assertEquals("The Shawshank Redemption", movie.getTitle());
			assertEquals(1994, movie.getYear());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getMovieById method with an invalid id. Null should be returned.
	 */
	@Test
	void testGetMovieByIdInvalidId() {
		try {
			Movie movie = movieDAO.getMovieById(1000);
			assertEquals(null, movie);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}

	/**
	 * Tests the getPeopleByMovieId method. We expect to get a list of people
	 * starring in the specified movie.
	 * 
	 */
	@Test
	void testGetPeopleByMovieId() {
		try {
			List<Person> stars = movieDAO.getPeopleByMovieId(1);
			assertEquals(
					"[Person [id=1, name=Tim Robbins, birth=1958], Person [id=2, name=Morgan Freeman, birth=1937]]",
					stars.toString());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}

	/**
	 * Tests the getPeopleByMovieId method with an invalid id. An empty list should
	 * be returned.
	 */
	@Test
	void testGetPeopleByMovieIdInvalidId() {
		try {
			List<Person> stars = movieDAO.getPeopleByMovieId(1000);
			assertEquals(Collections.emptyList(), stars);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}

	/**
	 * Tests the getRatingsByYear method. We expect to get a list of movies from
	 * 1994 in descending order of rating.
	 *
	 */
	@Test
	void testGetRatingsByYear() {
		try {
			List<MovieRating> movies = movieDAO.getRatingsByYear(1994);
			assertEquals("[1|The Shawshank Redemption|9.3|2200000|1994, 6|The River Wild|7.2|300000|1994]",
					movies.toString());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}

	/**
	 * Tests the getRatingsByYear method with limit on number of results and number
	 * of votes. We expect to 1 from 1994 with over 1000 votes.
	 *
	 */
	@Test
	void testGetRatingsByYearWithLimit() {
		try {
			List<MovieRating> movies = movieDAO.getRatingsByYear(1994, 1, 1000);
			assertEquals("[1|The Shawshank Redemption|9.3|2200000|1994]", movies.toString());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}

	/**
	 * Tests the getRatingsByYear method with an invalid year. An empty list should
	 * be returned.
	 */
	@Test
	void testGetRatingsByYearInvalidYear() {
		try {
			List<MovieRating> movies = movieDAO.getRatingsByYear(2027);
			assertEquals(Collections.emptyList(), movies);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}

	/**
	 * Tests the getRatingsByYear method with an invalid year. An empty list should
	 * be returned.
	 */
	@Test
	void testGetRatingsByYearWithLimitInvalidYear() {
		try {
			List<MovieRating> movies = movieDAO.getRatingsByYear(2027, 20, 1000);
			assertEquals(Collections.emptyList(), movies);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}

	@AfterEach
	void tearDown() {
		seeder.closeConnection();
	}

}