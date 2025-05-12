package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.dao.MovieDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

/**
 * The controller for the movie endpoints.
 * 
 * The controller acts as an intermediary between the HTTP routes and the DAO.
 * 
 * As you can see each method in the controller class is responsible for
 * handling a specific HTTP request.
 * 
 * Methods a Javalin Context object as a parameter and uses it to send a
 * response back to the client. We also handle business logic in the controller,
 * such as validating input and handling errors.
 *
 * Notice that the methods don't return anything. Instead, they use the Javalin
 * Context object to send a response back to the client.
 */

public class MovieController {

	/**
	 * The movie data access object.
	 */

	private final MovieDAO movieDAO;

	/**
	 * Constructs a MovieController object and initialises the movieDAO.
	 */
	public MovieController(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	/**
	 * Returns a list of all movies in the database, and limits the results if one is provided.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getAllMovies(Context ctx) {
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

			ctx.json(movieDAO.getAllMovies(limit));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getMovieById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Movie movie = movieDAO.getMovieById(id);
			if (movie == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movieDAO.getMovieById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the people starring in a specified movie.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getPeopleByMovieId(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			List<Person> stars = movieDAO.getPeopleByMovieId(id);
			if (stars.isEmpty()) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(stars);
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the movies in a certain year ordered by rating.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getRatingsByYear(Context ctx) {

		int year;
		
		try {
			year = Integer.parseInt(ctx.pathParam("year"));
			;
			if (year <= 0 || year >= 2025) {
				ctx.status(400);
				ctx.result("Year not valid.");
				return;
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Year not valid.");
			return;
		}

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

		String givenVoteLimit = ctx.queryParam("votes");
		int voteLimit = 1000;

		if (givenVoteLimit != null) {
			try {
				voteLimit = Integer.parseInt(givenVoteLimit);
				if (voteLimit <= 0) {
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
		
		try {
			List<MovieRating> movies = movieDAO.getRatingsByYear(year, limit, voteLimit);
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