package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.dao.MovieDAO;
import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

/**
 * The controller for the person endpoints.
 * 
 */

public class PersonController {
	
	/**
	 * The person data access object.
	 */

	private final PersonDAO personDAO;

	/**
	 * Constructs a PersonController object and initialises the personDAO.
	 */
	public PersonController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	/**
	 * Returns a list of all people in the database, and limits the results if one is provided.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getAllPeople(Context ctx) {
		try {
			String givenLimit = ctx.queryParam("limit");
            int limit = 50;
            
            if (givenLimit != null) {
            	try {
            		limit = Integer.parseInt(givenLimit);
            		if (limit <= 0) {
            			ctx.status(400);
            			ctx.result("Limit must be a positive integer.");
            			return;
            		}
            	} catch (NumberFormatException e) {
            		ctx.status(400);
        			ctx.result("Limit must be a positive integer.");
        			return;
            	}
            }
            
			ctx.json(personDAO.getAllPeople(limit));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the person with the specified id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getPersonById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Person person = personDAO.getPersonById(id);
			if (person == null) {
				ctx.status(404);
				ctx.result("Person not found");
				return;
			}
			ctx.json(personDAO.getPersonById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the movies that the specified person stars in.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getMoviesStarringPerson(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			List<Movie> movies = personDAO.getMoviesStarringPerson(id);
			if (movies.isEmpty()) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movies);
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

}