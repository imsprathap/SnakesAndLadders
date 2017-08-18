package org.target.snakesAndLadders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class will contain util functions 
 * @author sprathap
 *
 */
public final class GameUtils {
	
	private GameUtils(){
		throw new IllegalStateException();
	}
	
	public static List<String> getPropertiesList() {
		List<String> propertyList = new ArrayList<>();
		propertyList.add(Constants.SNAKE);
		propertyList.add(Constants.LADDER);
		propertyList.add(Constants.TRAMPOLINE);
		propertyList.add(Constants.SPRING);
		propertyList.add(Constants.PITSTOP);
		propertyList.add(Constants.EMPTY);
		return propertyList;
	}

	public static int generateRandom(int startNo, int endNo) {
		return ThreadLocalRandom.current().nextInt(startNo, endNo + 1);
	}
}
