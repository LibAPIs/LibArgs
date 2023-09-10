package com.mclarkdev.tools.libargs;

import java.util.ArrayList;

/**
 * LibArgs // LibArgs
 * 
 * A simple java helper library for processing command line arguments.
 *
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

		for (int x = 0; x < args.size(); x++) {

			if (!String.format("--%s", key).equals(args.get(x))) {
				continue;
			}
			x++;
			if (x >= args.size()) {
				throw new IllegalArgumentException("argument missing value");
			}
			return args.get(x);
		}
		return defVal;
	}

	/**
	 * Retrieve an integer parameter from the parsed arguments.
	 * 
	 * @param key    the parameter key
	 * @param defVal default value
	 * @return parameter value, or default
	 */
	public int getInteger(String key, int defVal) {
		try {
			return Integer.parseInt(getString(key, Integer.toString(defVal)));
		} catch (Exception e) {
			throw new IllegalArgumentException("bad value for key");
		}
	}

	/**
	 * Retrieve a boolean value from the parsed arguments.
	 * 
	 * @param key the parameter key
	 * @return parameter exists
	 */
	public boolean getBoolean(String key) {

		for (int x = 0; x < args.size(); x++) {

			if (!String.format("--%s", key).equals(args.get(x))) {
				continue;
			}
			return true;
		}
		return false;
	}
}
