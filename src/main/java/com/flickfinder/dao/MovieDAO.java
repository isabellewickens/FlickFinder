package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

/**
 * The Data Access Object for the Movie table.
 * 
 * This class is responsible for getting data from the Movies table in the
 * database.
 * 
 */
public class MovieDAO {

	/**
	 * The connection to the database.
	 */
	private final Connection connection;

	/**
	 * Constructs a SQLiteMovieDAO object and gets the database connection.
	 * 
	 */
	public MovieDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}

	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @return a list of all movies in the database
	 * @throws SQLException if a database error occurs
	 */

	public List<Movie> getAllMovies() throws SQLException {
		List<Movie> movies = new ArrayList<>();

		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from movies LIMIT 50");

		while (rs.next()) {
			movies.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year")));
		}

		return movies;
	}

	/**
	 * Returns a list of a limited number of movies in the database.
	 * Overrides the original function without a limit.
	 * 
	 * @return a list of a limited number of movies in the database
	 * @throws SQLException if a database error occurs
	 */
	public List<Movie> getAllMovies(int limit) throws SQLException {
		List<Movie> movies = new ArrayList<>();

		String statement = "select * from movies LIMIT ?";

		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, limit);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			movies.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year")));
		}

		return movies;
	}

	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param id the id of the movie
	 * @return the movie with the specified id
	 * @throws SQLException if a database error occurs
	 */
	public Movie getMovieById(int id) throws SQLException {

		String statement = "select * from movies where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			return new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"));
		}

		// return null if the id does not return a movie.

		return null;

	}

	/**
	 * Returns the stars of a movie with the specified id.
	 * 
	 * @param id the id of the movie
	 * @return the list of people
	 * @throws SQLException if a database error occurs
	 */
	public List<Person> getPeopleByMovieId(int id) throws SQLException {

		List<Person> stars = new ArrayList<>();
		String statement = "select * from people where id in (select person_id from stars where movie_id = ?)";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			stars.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));

		}

		return stars;

	}

	/**
	 * Returns the movies released in a specified year, ordered by their rating.
	 * 
	 * @param year the year of the movie
	 * @return the movies from the specified year
	 * @throws SQLException if a database error occurs
	 */
	public List<MovieRating> getRatingsByYear(int year) throws SQLException {

		List<MovieRating> movies = new ArrayList<>();
		// SQL joins revised using: https://www.w3schools.com/sql/sql_join.asp
		String statement = "SELECT movies.id, movies.title, ratings.rating, ratings.votes, movies.year FROM movies JOIN ratings ON movies.id = ratings.movie_id WHERE movies.year = ? ORDER BY ratings.rating DESC";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, year);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			movies.add(new MovieRating(rs.getInt("id"), rs.getString("title"), rs.getDouble("rating"),
					rs.getInt("votes"), rs.getInt("year")));

		}

		return movies;

	}

	/**
	 * Returns the movies released in a specified year, ordered by their rating.
	 * Returned results are limited by number of votes and result limit.
	 * 
	 * @param year the year of the movie
	 * @param limit the number of results
	 * @param voteLimit the limit to number of votes
	 * @return the movies from the specified year
	 * @throws SQLException if a database error occurs
	 */
	public List<MovieRating> getRatingsByYear(int year, int limit, int voteLimit) throws SQLException {

		List<MovieRating> movies = new ArrayList<>();
		String statement = "SELECT movies.id, movies.title, ratings.rating, ratings.votes, movies.year FROM movies JOIN ratings ON movies.id = ratings.movie_id WHERE movies.year = ? AND ratings.votes >= ? ORDER BY ratings.rating DESC LIMIT ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, year);
		ps.setInt(2, voteLimit);
		ps.setInt(3, limit);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			movies.add(new MovieRating(rs.getInt("id"), rs.getString("title"), rs.getDouble("rating"),
					rs.getInt("votes"), rs.getInt("year")));

		}

		return movies;

	}
}
