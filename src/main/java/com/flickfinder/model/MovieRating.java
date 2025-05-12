package com.flickfinder.model;

/**
 * Represents a movie with a rating and number of votes.
 * Extends the Movie class.
 */

public class MovieRating extends Movie {
	
	private double rating;
	private int votes;

	
	/**
	 * Constructs a Movie Rating object with the specified id, title, rating, votes and year.
	 *
	 * @param id   	 	the unique identifier of the movie
	 * @param title 	the title of the movie
	 * @param rating	the rating of the movie
	 * @param votes 	the number of votes the movies has
	 * @param year  	the release year of the movie
	 */
	public MovieRating(int id, String title, double rating, int votes, int year) {
		super(id, title, year);
		this.rating = rating;
		this.votes = votes;
	}

	/**
	 * Returns the rating of the movie.
	 *
	 * @return the rating of the movie
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * Sets the rating of the movie.
	 *
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * Returns the votes of the movie.
	 *
	 * @return the votes of the movie
	 */
	public int getVotes() {
		return votes;
	}

	/**
	 * Sets the votes of the movie.
	 *
	 * @param votes the votes to set
	 */
	public void setVotes(int votes) {
		this.votes = votes;
	}

	/**
	 * Returns a string representation of the Movie Rating object.
	 * This is primarily used for debugging purposes.
	 *
	 * @return a string representation of the Movie Rating object
	 */
	@Override
	public String toString() {
		return getId() + "|" + getTitle() + "|" + getRating() + "|" + getVotes() + "|" + getYear();
	}
	
	

}
