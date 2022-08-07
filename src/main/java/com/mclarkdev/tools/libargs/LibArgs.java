package com.mclarkdev.tools.libargs;

import java.util.ArrayList;

public class LibArgs {

	private static final LibArgs libArgs = new LibArgs(null);;

	public static LibArgs instance() {
		return libArgs;
	}

	private ArrayList<String> args = new ArrayList<>();

	public LibArgs(String[] args) {
		parse(args);
	}

	public LibArgs parse(String[] args) {

		if (args == null) {
			return this;
		}

		for (String arg : args) {
			this.args.add(arg);
		}

		return this;
	}

	public int getInteger(String key, int defVal) {
		try {
			return Integer.parseInt(getString(key, Integer.toString(defVal)));
		} catch (Exception e) {
			throw new IllegalArgumentException("bad value for key");
		}
	}

	public String getString(String key) {
		return getString(key, null);
	}

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
