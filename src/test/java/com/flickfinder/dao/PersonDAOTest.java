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
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

/**
 * Test for the Person Data Access Object. This uses an in-memory database for
 * testing purposes.
 */
class PersonDAOTest {

	/**
	 * The person data access object.
	 */

	private PersonDAO personDAO;

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
		personDAO = new PersonDAO();

	}

	/**
	 * Tests the getAllPeople method. We expect to get a list of all people in the
	 * database. We have seeded the database with 5 people, so we expect to get 5
	 * people back. At this point, we avoid checking the actual content of the list.
	 */
	@Test
	void testGetAllPeople() {
		try {
			List<Person> people = personDAO.getAllPeople();
			assertEquals(5, people.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getAllPeople method when we want to limit the number of results
	 * returned. We expect to receive just 2 results.
	 */
	@Test
	void testGetAllPeopleWithLimit() {
		try {
			List<Person> people = personDAO.getAllPeople(3);
			assertEquals(3, people.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getPersonById method. We expect to get the person with the
	 * specified id.
	 */
	@Test
	void testGetPersonById() {
		Person person;
		try {
			person = personDAO.getPersonById(1);
			assertEquals("Tim Robbins", person.getName());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getPersonById method with an invalid id. Null should be returned.
	 */
	@Test
	void testGetPersonByIdInvalidId() {
		try {
			Person person = personDAO.getPersonById(1000);
			assertEquals(null, person);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}

	/**
	 * Tests the getMoviesStarringPerson method. We expect to get a movies that the
	 * specified person stars in.
	 * 
	 */
	@Test
	void testGetMoviesStarringPerson() {
		try {
			List<Movie> movies = personDAO.getMoviesStarringPerson(4);
			assertEquals(
					"[Movie [id=2, title=The Godfather, year=1972], Movie [id=3, title=The Godfather: Part II, year=1974]]",
					movies.toString());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}

	/**
	 * Tests the getMoviesStarringPerson method with an invalid id. An empty list
	 * should be returned.
	 */
	@Test
	void testGetMoviesStarringPersonInvalidId() {
		try {
			List<Movie> movies = personDAO.getMoviesStarringPerson(7);
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
