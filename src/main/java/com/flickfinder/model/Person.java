package com.flickfinder.model;

/**
 * A person in the movie database.
 * 
 * @TODO: Implement this class
 */
public class Person {
	
	private int id;
	private String name;
	private int birth;


	/**
	 * Constructs a Movie object with the specified id, title, and year.
	 *
	 * @param id    the unique identifier of the movie
	 * @param title the title of the movie
	 * @param year  the release year of the movie
	 */
	public Person(int id, String name, int birth) {
		this.id = id;
		this.name = name;
		this.birth = birth;
	}

	/**
	 * Returns the unique identifier of the person.
	 *
	 * @return the id of the person
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the person.
	 *
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the name of the person.
	 *
	 * @return the name of the person
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the person.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the year of birth of the person.
	 *
	 * @return the birth of the person
	 */
	public int getBirth() {
		return birth;
	}

	/**
	 * Sets the birth year of the person.
	 *
	 * @param birth the birth year to set
	 */
	public void setBirth(int birth) {
		this.birth = birth;
	}

	/**
	 * Returns a string representation of the Person object.
	 * This is primarily used for debugging purposes.
	 *
	 * @return a string representation of the Person object
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birth=" + birth + "]";
	}


}
