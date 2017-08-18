package org.target.snakesAndLadders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.target.snakesAndLadders.model.PropertyModel;

/**
 * This class will create Board to play the game
 * @author sprathap
 *
 */
public class BoardGenerator {

	public Map<Integer, PropertyModel> getBoard(int nSquares) {
		Map<Integer, PropertyModel> boardMap = new HashMap<Integer, PropertyModel>();
		for (int i = 1; i <= nSquares; i++) {
			boardMap.put(i, getProperties(i, nSquares));
		}
		return boardMap;
	}

	private PropertyModel getProperties(int startPos, int endPos) {
		List<String> propertyList = GameUtils.getPropertiesList();
		PropertyModel propertyModel = new PropertyModel();
		String propertyName = "";

		if ((startPos == 1) || (startPos == endPos)) {
			propertyName = Constants.EMPTY;
		} else {
			propertyName = propertyList.get(GameUtils.generateRandom(0, 5));
		}
		propertyModel.setPropertyType(propertyName);

		int nextPos = 0;
		if (null != propertyName && !"".equalsIgnoreCase(propertyName)
				&& Constants.SNAKE.equalsIgnoreCase(propertyName)) {
			nextPos = GameUtils.generateRandom(1, startPos - 1);
		} else if (null != propertyName && !"".equalsIgnoreCase(propertyName)
				&& Constants.LADDER.equalsIgnoreCase(propertyName)) {
			nextPos = GameUtils.generateRandom(startPos + 1, endPos);
		}
		propertyModel.setNextPosition(nextPos);

		return propertyModel;
	}
}
