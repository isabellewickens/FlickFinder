package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

/**
 * The Data Access Object for the Person table.
 * 
 * This class is responsible for getting data from the Movies table in the
 * database.
 * 
 */
public class PersonDAO {

	/**
	 * The connection to the database.
	 */
	private final Connection connection;

	/**
	 * Constructs a SQLitePersonDAO object and gets the database connection.
	 * 
	 */
	public PersonDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}

	/**
	 * Returns a list of all people in the database.
	 * 
	 * @return a list of all people in the database
	 * @throws SQLException if a database error occurs
	 */

	public List<Person> getAllPeople() throws SQLException {
		List<Person> people = new ArrayList<>();

		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from people LIMIT 50");

		while (rs.next()) {
			people.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
		}

		return people;
	}

	/**
	 * Returns a list of a limited number of people in the database.
	 * Overrides the original function without a limit.
	 * 
	 * @return a list of a limited number of people in the database
	 * @throws SQLException if a database error occurs
	 */
	public List<Person> getAllPeople(int limit) throws SQLException {
		List<Person> people = new ArrayList<>();

		String statement = "select * from people LIMIT ?";

		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, limit);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			people.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
		}

		return people;
	}

	/**
	 * Returns the person with the specified id.
	 * 
	 * @param id the id of the person
	 * @return the person with the specified id
	 * @throws SQLException if a database error occurs
	 */
	public Person getPersonById(int id) throws SQLException {

		String statement = "select * from people where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			return new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth"));
		}

		// return null if the id does not return a movie.

		return null;

	}

	/**
	 * Returns the movies starring a specified person.
	 * 
	 * @param id the id of the person
	 * @return the list of movies starring specified person
	 * @throws SQLException if a database error occurs
	 */
	public List<Movie> getMoviesStarringPerson(int id) throws SQLException {

		List<Movie> movies = new ArrayList<>();
		String statement = "select * from movies where id in (select movie_id from stars where person_id = ?)";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			movies.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year")));

		}

		return movies;

	}

}
