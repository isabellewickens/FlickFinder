package com.flickfinder.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.dao.PersonDAO;

import io.javalin.http.Context;

/**
 * Test for the Person Controller
 */
class PersonControllerTest {

	/**
	 *
	 * The context object, later we will mock it.
	 */
	private Context ctx;

	/**
	 * The person data access object.
	 */
	private PersonDAO personDAO;

	/**
	 * The person controller.
	 */
	private PersonController personController;

	@BeforeEach
	void setUp() {
		personDAO = mock(PersonDAO.class);
		ctx = mock(Context.class);

		personController = new PersonController(personDAO);
	}

	/**
	 * Tests the getAllPeople method.
	 * We expect to get a list of all people in the database.
	 */
	@Test
	void testGetAllPeople() {
		when(ctx.queryParam("limit")).thenReturn(null);
		personController.getAllPeople(ctx);
		try {
			verify(personDAO).getAllPeople(50);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getAllPeople method with a limit.
	 * We expect to get a list of a specified number of people in the database.
	 */
	@Test
	void testGetAllPeopleLimit() {
		when(ctx.queryParam("limit")).thenReturn("10");
		personController.getAllPeople(ctx);
		try {
			verify(personDAO).getAllPeople(10);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test that the controller returns a 500 status code when a database error
	 * occurs
	 * 
	 * @throws SQLException
	 */
	@Test
	void testThrows500ExceptionWhenGetAllDatabaseError() throws SQLException {
		when(personDAO.getAllPeople(50)).thenThrow(new SQLException());
		personController.getAllPeople(ctx);
		verify(ctx).status(500);
	}

	/**
	 * Test that the controller returns a 400 status code when an invalid limit is entered.
	 * 
	 * @throws SQLException
	 */
	@Test
	void testGetAllPeopleWithInvalidLimit() throws SQLException {
	    when(ctx.queryParam("limit")).thenReturn("invalid");
	    personController.getAllPeople(ctx);
	    verify(ctx).status(400);
	    verify(ctx).result("Limit must be a positive integer.");
	}

	/**
	 * Test that the controller returns a 400 status code when an invalid limit is entered.
	 * 
	 * @throws SQLException
	 */
	@Test
	void testGetAllPeopleWithNegativeLimit() throws SQLException {
	    when(ctx.queryParam("limit")).thenReturn("-2");
	    personController.getAllPeople(ctx);
	    verify(ctx).status(400);
	    verify(ctx).result("Limit must be a positive integer.");
	}

	/**
	 * Tests the getPersonById method.
	 * We expect to get the person with the specified id.
	 */
	@Test
	void testGetPersonById() {
		when(ctx.pathParam("id")).thenReturn("1");
		personController.getPersonById(ctx);
		try {
			verify(personDAO).getPersonById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test that the controller returns a 500 status code when a database error
	 * occurs
	 * 
	 * @throws SQLException
	 */
	@Test
	void testThrows500ExceptionWhenGetByIdDatabaseError() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenThrow(new SQLException());
		personController.getPersonById(ctx);
		verify(ctx).status(500);
	}

	/**
	 * Test that the controller returns a 404 status code when a movie is not found
	 * or
	 * database error.
	 * 
	 * @throws SQLException
	 */
	@Test
	void testThrows404ExceptionWhenNoPersonFound() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenReturn(null);
		personController.getPersonById(ctx);
		verify(ctx).status(404);
	}

	/**
	 * Tests the GetMoviesStarringPerson method.
	 * We expect get a list of movies starring a specific person.
	 */
	@Test
	void testGetMoviesStarringPerson() {
		when(ctx.pathParam("id")).thenReturn("1");
		personController.getMoviesStarringPerson(ctx);
		try {
			verify(personDAO).getMoviesStarringPerson(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test that the controller returns a 500 status code when a database error
	 * occurs
	 * 
	 * @throws SQLException
	 */
	@Test
	void testThrows500ExceptionWhenGetMoviesStarringPersonDatabaseError() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getMoviesStarringPerson(1)).thenThrow(new SQLException());
		personController.getMoviesStarringPerson(ctx);
		verify(ctx).status(500);
	}

	/**
	 * Test that the controller returns a 404 status code when a movie is not found
	 * or
	 * database error.
	 * 
	 * @throws SQLException
	 */
	@Test
	void testThrows404ExceptionWhenNoPersonFound2() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getMoviesStarringPerson(1)).thenReturn(Collections.emptyList());
		personController.getMoviesStarringPerson(ctx);
		verify(ctx).status(404);
	}

}