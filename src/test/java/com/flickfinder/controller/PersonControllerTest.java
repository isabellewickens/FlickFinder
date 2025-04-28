package com.flickfinder.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.dao.PersonDAO;

import io.javalin.http.Context;

/**
 * TODO: Implement this class
 */
class PersonControllerTest {

	private Context ctx;

	private PersonDAO personDAO;

	private PersonController personController;

	@BeforeEach
	void setUp() {
		personDAO = mock(PersonDAO.class);
		ctx = mock(Context.class);

		personController = new PersonController(personDAO);
	}

	@Test
	void testGetAllMovies() {
		when(ctx.queryParam("limit")).thenReturn(null);
		personController.getAllPeople(ctx);
		try {
			verify(personDAO).getAllPeople(50);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
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


	@Test
	void testThrows500ExceptionWhenGetAllDatabaseError() throws SQLException {
		when(personDAO.getAllPeople(50)).thenThrow(new SQLException());
		personController.getAllPeople(ctx);
		verify(ctx).status(500);
	}

	@Test
	void testGetMovieById() {
		when(ctx.pathParam("id")).thenReturn("1");
		personController.getPersonById(ctx);
		try {
			verify(personDAO).getPersonById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testThrows500ExceptionWhenGetByIdDatabaseError() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenThrow(new SQLException());
		personController.getPersonById(ctx);
		verify(ctx).status(500);
	}


	@Test
	void testThrows404ExceptionWhenNoPersonFound() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenReturn(null);
		personController.getPersonById(ctx);
		verify(ctx).status(404);
	}
	
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
	
	@Test
	void testThrows500ExceptionWhenGetMoviesStarringPersonDatabaseError() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getMoviesStarringPerson(1)).thenThrow(new SQLException());
		personController.getMoviesStarringPerson(ctx);
		verify(ctx).status(500);
	}


	@Test
	void testThrows404ExceptionWhenNoPersonFound2() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getMoviesStarringPerson(1)).thenReturn(null);
		personController.getMoviesStarringPerson(ctx);
		verify(ctx).status(404);
	}

}