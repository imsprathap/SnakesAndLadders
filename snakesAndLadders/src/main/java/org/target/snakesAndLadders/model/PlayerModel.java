package org.target.snakesAndLadders.model;

public class PlayerModel {
	private String playerName;
	private int currentPos;
	private int health;
	private int currentDiceValue;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getCurrentDiceValue() {
		return currentDiceValue;
	}

	public void setCurrentDiceValue(int currentDiceValue) {
		this.currentDiceValue = currentDiceValue;
	}
	
}
