package org.target.snakesAndLadders;

/**
 * This class will have constants 
 * @author sprathap
 *
 */
public final class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String SNAKE = "SNAKE";
	public static final String LADDER = "LADDER";
	public static final String TRAMPOLINE = "TRAMPOLINE";
	public static final String SPRING = "SPRING";
	public static final String PITSTOP = "PITSTOP";
	public static final String EMPTY = "EMPTY";
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\033[1;91m";

}
