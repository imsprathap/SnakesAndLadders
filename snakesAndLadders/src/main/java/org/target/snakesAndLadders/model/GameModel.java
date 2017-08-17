package org.target.snakesAndLadders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameModel {

	private int nBlocks;

	private List<PlayerModel> playersList = new ArrayList<>();
	
	Map<Integer, PropertyModel> gameBoardMap = new HashMap<>();

	public int getnBlocks() {
		return nBlocks;
	}

	public void setnBlocks(int nBlocks) {
		this.nBlocks = nBlocks;
	}

	public List<PlayerModel> getPlayersList() {
		return playersList;
	}

	public void setPlayersList(List<PlayerModel> playersList) {
		this.playersList = playersList;
	}

	public Map<Integer, PropertyModel> getGameBoardMap() {
		return gameBoardMap;
	}

	public void setGameBoardMap(Map<Integer, PropertyModel> gameBoardMap) {
		this.gameBoardMap = gameBoardMap;
	}

}
