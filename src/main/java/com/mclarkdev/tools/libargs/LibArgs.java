package com.mclarkdev.tools.libargs;

public class LibArgs {

	private String[] args;

	public LibArgs(String[] args) {

		if (args == null) {
			args = new String[0];
		}

		this.args = args;
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

		for (int x = 0; x < args.length; x++) {

			if (!String.format("--%s", key).equals(args[x])) {
				continue;
			}
			x++;
			if (x >= args.length) {
				throw new IllegalArgumentException("argument missing value");
			}
			return args[x];
		}
		return defVal;
	}

	public boolean getBoolean(String key) {

		for (int x = 0; x < args.length; x++) {

			if (!String.format("--%s", key).equals(args[x])) {
				continue;
			}
			return true;
		}
		return false;
	}
}
