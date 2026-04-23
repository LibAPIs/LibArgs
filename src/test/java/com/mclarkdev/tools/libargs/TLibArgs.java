package com.mclarkdev.tools.libargs;

import org.junit.Assert;
import org.junit.Test;

public class TLibArgs {

	@Test
	public void testLibArgsNullArgs() {

		LibArgs libArgs = new LibArgs(null);

		Assert.assertEquals(null, libArgs.getString("string-one"));
		Assert.assertEquals(true, libArgs.getBoolean("bool-one", true));
		Assert.assertEquals(7, libArgs.getInteger("integer-one", 7));
		Assert.assertEquals(2.5, libArgs.getDouble("double-one", 2.5), 0.0);
	}

	@Test
	public void testLibArgsInstanceReturnsSingleton() {

		Assert.assertSame(LibArgs.instance(), LibArgs.instance());
		Assert.assertEquals(null, LibArgs.instance().getString("missing-key"));
	}

	@Test
	public void testLibArgsParseAppendsArguments() {

		LibArgs libArgs = new LibArgs(new String[] { //
				"--string-one", "abc" });

		libArgs.parse(new String[] { //
				"--integer-one", "99", "--double-one", "3.5" });

		Assert.assertEquals("abc", libArgs.getString("string-one"));
		Assert.assertEquals(99, libArgs.getInteger("integer-one", -1));
		Assert.assertEquals(3.5, libArgs.getDouble("double-one", -1.0), 0.0);
	}

	@Test
	public void testLibArgsIndexOfAndIsSet() {

		String[] args = new String[] { //
				"--string-one", "abc", "--bool-one", "true" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(0, libArgs.indexOf("string-one"));
		Assert.assertEquals(2, libArgs.indexOf("bool-one"));
		Assert.assertEquals(-1, libArgs.indexOf("missing"));
		Assert.assertEquals(true, libArgs.isSet("string-one"));
		Assert.assertEquals(false, libArgs.isSet("missing"));
	}

	@Test
	public void testLibArgsNoString() {

		String[] args = new String[] {};

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(null, libArgs.getString("string-one"));
		Assert.assertEquals(null, libArgs.getString("string-one"));
	}

	@Test
	public void testLibArgsStringDefaultWhenMissing() {

		LibArgs libArgs = new LibArgs(new String[] {});
		Assert.assertEquals("fallback", libArgs.getString("string-one", "fallback"));
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

	@Test
	public void testLibArgsDuplicateStringUsesFirstMatch() {

		String[] args = new String[] { //
				"--string-one", "first", "--string-one", "second" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals("first", libArgs.getString("string-one"));
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
		Assert.assertEquals(false, libArgs.getBoolean("bool-one", false));
		Assert.assertEquals(true, libArgs.getBoolean("bool-one", true));
	}

	@Test
	public void testLibArgsOneBoolean() {

		String[] args = new String[] { //
				"--bool-one", "false" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(false, libArgs.getBoolean("bool-one", true));
		
		Assert.assertEquals(false, libArgs.getBoolean("bool-two", false));
		Assert.assertEquals(false, libArgs.getBoolean("bool-three", false));
		Assert.assertEquals(false, libArgs.getBoolean("bool-four", false));
	}

	@Test
	public void testLibArgsTwoBoolean() {

		String[] args = new String[] { //
				"--bool-one", "false", "--bool-two", "true" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(false, libArgs.getBoolean("bool-one", true));
		Assert.assertEquals(true, libArgs.getBoolean("bool-two", false));
		
		Assert.assertEquals(false, libArgs.getBoolean("bool-three", false));
		Assert.assertEquals(true, libArgs.getBoolean("bool-four", true));
	}

	@Test
	public void testLibArgsBooleanInvalidStringReturnsFalse() {

		String[] args = new String[] { //
				"--bool-one", "not-a-boolean" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(false, libArgs.getBoolean("bool-one", true));
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
	public void testLibArgsNoDouble() {

		String[] args = new String[] {};

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(-1.5, libArgs.getDouble("double-one", -1.5), 0.0);
		Assert.assertEquals(9.25, libArgs.getDouble("double-one", 9.25), 0.0);
	}

	@Test
	public void testLibArgsOneDouble() {

		String[] args = new String[] { //
				"--double-one", "99.75" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(99.75, libArgs.getDouble("double-one", -1.0), 0.0);
		Assert.assertEquals(-1.0, libArgs.getDouble("double-two", -1.0), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLibArgsOneBadDouble() {

		String[] args = new String[] { //
				"--double-one" };

		LibArgs libArgs = new LibArgs(args);
		libArgs.getDouble("double-one", -1.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLibArgsOneDoubleGivenString() {

		String[] args = new String[] { //
				"--double-one", "abc" };

		LibArgs libArgs = new LibArgs(args);
		libArgs.getDouble("double-one", -1.0);
	}

	@Test
	public void testLibArgsOneStringOneBoolOneInteger() {

		String[] args = new String[] { //
				"--string-one", "abc", "--bool-one", "false", "--integer-one", "99" };

		LibArgs libArgs = new LibArgs(args);
		Assert.assertEquals(args[1], libArgs.getString("string-one"));
		Assert.assertEquals(false, libArgs.getBoolean("bool-one", true));
		Assert.assertEquals(99, libArgs.getInteger("integer-one", -1));
		Assert.assertEquals(null, libArgs.getString("string-two"));
		Assert.assertEquals(true, libArgs.getBoolean("bool-two", true));
		Assert.assertEquals(-1, libArgs.getInteger("integer-two", -1));
	}
}
