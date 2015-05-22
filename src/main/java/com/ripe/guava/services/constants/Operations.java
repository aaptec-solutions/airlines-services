package com.ripe.guava.services.constants;

/**
 * Enum for all the operations to be handled by the delegate
 * in the services application
 * @author ankit.verma
 *
 */
public enum Operations {
	
	/**
	 * Operation to apply fdtl rules.
	 */
	APPLY_FDTL("apply_fdtl");
	

	/**
	 * value of the enum constant.
	 */
	private final String value;

	/**
	 * Custom Constructor.
	 * 
	 * @param value
	 *            the value
	 */
	private Operations(final String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value.
	 */
	public String value() {
		return this.value;
	}
}
