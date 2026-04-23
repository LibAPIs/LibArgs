package com.mclarkdev.tools.libargs;

import java.util.ArrayList;

/**
 * LibArgs // LibArgs
 * 
 * A simple java helper library for processing command line arguments.
 * 
 * @author Matthew R. Clark (MClarkDev.com, 2021-2026)
 */
public class LibArgs {

	private static final LibArgs libArgs = new LibArgs(null);;

	/**
	 * Get the static instance of the LibArgs object.
	 * 
	 * @return LibArgs object
	 */
	public static LibArgs instance() {
		return libArgs;
	}

	private ArrayList<String> args = new ArrayList<>();

	/**
	 * Create an instance of LibArgs with a given set of arguments.
	 * 
	 * @param args the arguments to parse
	 */
	public LibArgs(String[] args) {
		parse(args);
	}

	/**
	 * Parse a given set of arguments.
	 * 
	 * @param args the arguments to parse
	 * @return the LibArgs object
	 */
	public LibArgs parse(String[] args) {

		if (args == null) {
			return this;
		}

		for (String arg : args) {
			this.args.add(arg);
		}

		return this;
	}

	/**
	 * Returns the index of the given key.
	 * 
	 * @param key the parameter key
	 * @return the index of the key, or -1
	 */
	public int indexOf(String key) {

		for (int x = 0; x < args.size(); x++) {
			if (String.format("--%s", key).equals(args.get(x))) {
				return x;
			}
		}
		return -1;
	}

	/**
	 * Returns true if the given parameter is set.
	 * 
	 * @param key the parameter key
	 * @return parameter is set
	 */
	public boolean isSet(String key) {

		return (indexOf(key) >= 0);
	}

	/**
	 * Retrieve a string parameter from the parsed arguments.
	 * 
	 * @param key the parameter key
	 * @return parameter value, or null
	 */
	public String getString(String key) {
		return getString(key, null);
	}

	/**
	 * Retrieve a string parameter from the parsed arguments.
	 * 
	 * @param key    the parameter key
	 * @param defVal default value
	 * @return parameter value, or default
	 */
	public String getString(String key, String defVal) {

		// Get the index of the key
		int index = indexOf(key);

		// Default if not set
		if (index == -1) {
			return defVal;
		}

		// Fail if out of bounds
		if ((index + 1) >= args.size()) {
			throw new IllegalArgumentException("argument missing value");
		}

		// Get and return the value
		return args.get((index + 1));
	}

	/**
	 * Retrieve an integer parameter from the parsed arguments.
	 * 
	 * @param key    the parameter key
	 * @param defVal default value
	 * @return parameter value, or default
	 */
	public int getInteger(String key, int defVal) {

		// Get value as string
		String value = getString(key, //
				Integer.toString(defVal));
		try {

			// Return parsed at integer
			return Integer.parseInt(value);
		} catch (Exception e) {

			// Throw parse failure
			throw new IllegalArgumentException(//
					String.format("%s=%s (!integer)", key, value));
		}
	}

	/**
	 * Retrieve a double parameter from the parsed arguments.
	 * 
	 * @param key    the parameter key
	 * @param defVal default value
	 * @return parameter value, or default
	 */
	public double getDouble(String key, double defVal) {

		// Get value as string
		String value = getString(key, Double.toString(defVal));
		try {

			// Return parsed as double
			return Double.parseDouble(value);
		} catch (Exception e) {

			// Throw parse failure
			throw new IllegalArgumentException(//
					String.format("%s=%s (!double)", key, value));
		}
	}

	/**
	 * Retrieve a boolean value from the parsed arguments.
	 * 
	 * @param key the parameter key
	 * @return parameter value, or default
	 */
	public boolean getBoolean(String key, boolean defVal) {

		// Get value as string
		String value = getString(key, Boolean.toString(defVal));
		try {

			// Return parsed at integer
			return Boolean.parseBoolean(value);
		} catch (Exception e) {

			// Throw parse failure
			throw new IllegalArgumentException(//
					String.format("%s=%s (!boolean)", key, value));
		}
	}
}
