package com.flickfinder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * Test for the Person Model
 * 
 */

class PersonTest {

	/**
	 * The person object to be tested.
	 */
	private Person person;

	/**
	 * Set up the person object before each test.
	 *
	 */
	@BeforeEach
	public void setUp() {
		person = new Person(1, "Zoe Weston", 2006);
	}

	/**
	 * Test the person object is created with the correct values.
	 */
	@Test
	public void testPersonCreated() {
		assertEquals(1, person.getId());
		assertEquals("Zoe Weston", person.getName());
		assertEquals(2006, person.getBirth());
	}

	/**
	 * Test the person object is created with the correct values.
	 */
	@Test
	public void testPersonSetters() {
		person.setId(2);
		person.setName("Gabriella Garbini");
		person.setBirth(2005);
		assertEquals(2, person.getId());
		assertEquals("Gabriella Garbini", person.getName());
		assertEquals(2005, person.getBirth());
	}

}