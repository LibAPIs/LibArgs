package com.mclarkdev.tools.libargs;

import org.junit.Assert;
import org.junit.Test;

public class TLibArgs {

	public void testLibArgsNullArgs() {

		LibArgs libArgs = new LibArgs(null);

		Assert.assertEquals(null, libArgs.getString("string-one"));
		Assert.assertEquals(false, libArgs.getBoolean("bool-one"));
	}

	@Test
	public void testLibArgsNoString() {

		String[] args = new String[] {};

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(null, libArgs.getString("string-one"));
		Assert.assertEquals(null, libArgs.getString("string-one"));
	}

	@Test
	public void testLibArgsOneString() {

		String[] args = new String[] { //
				"--string-one", "one" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(args[1], libArgs.getString("string-one"));
		Assert.assertEquals(null, libArgs.getString("string-two"));
		Assert.assertEquals(args[1], libArgs.getString("string-one"));
		Assert.assertEquals(null, libArgs.getString("string-two"));
	}

	@Test
	public void testLibArgsTwoString() {

		String[] args = new String[] { //
				"--string-one", "abc", "--string-two", "def" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(args[1], libArgs.getString("string-one"));
		Assert.assertEquals(args[3], libArgs.getString("string-two"));
		Assert.assertEquals(args[1], libArgs.getString("string-one"));
		Assert.assertEquals(args[3], libArgs.getString("string-two"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLibArgsOneBadString() {

		String[] args = new String[] { //
				"--string-one" };

		LibArgs libArgs = new LibArgs(args);
		libArgs.getString("string-one");
	}

	@Test
	public void testLibArgsNoBoolean() {

		String[] args = new String[] {};

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(false, libArgs.getBoolean("bool-one"));
		Assert.assertEquals(false, libArgs.getBoolean("bool-one"));
	}

	@Test
	public void testLibArgsOneBoolean() {

		String[] args = new String[] { //
				"--bool-one" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(true, libArgs.getBoolean("bool-one"));
		Assert.assertEquals(false, libArgs.getBoolean("bool-two"));
		Assert.assertEquals(true, libArgs.getBoolean("bool-one"));
		Assert.assertEquals(false, libArgs.getBoolean("bool-two"));
	}

	@Test
	public void testLibArgsTwoBoolean() {

		String[] args = new String[] { //
				"--bool-one", "--bool-two" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(true, libArgs.getBoolean("bool-one"));
		Assert.assertEquals(true, libArgs.getBoolean("bool-two"));
		Assert.assertEquals(true, libArgs.getBoolean("bool-one"));
		Assert.assertEquals(true, libArgs.getBoolean("bool-two"));
	}

	@Test
	public void testLibArgsNoInteger() {

		String[] args = new String[] {};

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(-1, libArgs.getInteger("integer-one", -1));
		Assert.assertEquals(99, libArgs.getInteger("integer-one", 99));
	}

	@Test
	public void testLibArgsOneInteger() {

		String[] args = new String[] { //
				"--integer-one", "99" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(99, libArgs.getInteger("integer-one", -1));
		Assert.assertEquals(-1, libArgs.getInteger("integer-two", -1));
		Assert.assertEquals(99, libArgs.getInteger("integer-one", -1));
		Assert.assertEquals(-1, libArgs.getInteger("integer-two", -1));
	}

	@Test
	public void testLibArgsTwoInteger() {

		String[] args = new String[] { //
				"--integer-one", "99", "--integer-two", "999" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(99, libArgs.getInteger("integer-one", -1));
		Assert.assertEquals(999, libArgs.getInteger("integer-two", -1));
		Assert.assertEquals(99, libArgs.getInteger("integer-one", -1));
		Assert.assertEquals(999, libArgs.getInteger("integer-two", -1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLibArgOneBadInteger() {

		String[] args = new String[] { //
				"--integer-one" };

		LibArgs libArgs = new LibArgs(args);
		libArgs.getInteger("integer-one", -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLibArgsOneIntegerGivenString() {

		String[] args = new String[] { //
				"--integer-one", "abc" };

		LibArgs libArgs = new LibArgs(args);
		libArgs.getInteger("integer-one", -1);
	}

	@Test
	public void testLibArgsOneStringOneBoolOneInteger() {

		String[] args = new String[] { //
				"--string-one", "abc", "--bool-one", "--integer-one", "99" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(args[1], libArgs.getString("string-one"));
		Assert.assertEquals(true, libArgs.getBoolean("bool-one"));
		Assert.assertEquals(99, libArgs.getInteger("integer-one", -1));
		Assert.assertEquals(null, libArgs.getString("string-two"));
		Assert.assertEquals(false, libArgs.getBoolean("bool-two"));
		Assert.assertEquals(-1, libArgs.getInteger("integer-two", -1));
	}
}
