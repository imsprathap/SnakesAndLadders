package org.target.snakesAndLadders.play;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.target.snakesAndLadders.BoardGenerator;
import org.target.snakesAndLadders.PlayGame;
import org.target.snakesAndLadders.model.GameModel;
import org.target.snakesAndLadders.model.PlayerModel;
import org.target.snakesAndLadders.model.PropertyModel;

/**
 * Main class to run the game, Snakes and Ladders
 * @author sprathap
 *
 */
public class Runner {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\033[1;91m";

	public static void main(String[] args) {
		System.out.println("************************************");
		System.out.println("Welcome To Snakes And Ladders");
		System.out.println("************************************");
		GameModel gameModel = new GameModel();
		Runner runner = new Runner();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter N squares you need in the game");
		int nBlocks = runner.getInteger(scanner, 0);
		gameModel.setnBlocks(nBlocks);
		System.out.println("CONGRATS!!! You have "+nBlocks+" squares in the game..!!\n\n");
		Map<Integer, PropertyModel> gameBoardMap = new BoardGenerator().getBoard(nBlocks);
		List<PlayerModel> playersList = runner.getPlayersName(scanner);
		gameModel.setPlayersList(playersList);
		gameModel.setGameBoardMap(gameBoardMap);
		
		new PlayGame().startGame(gameModel, scanner);
		
		scanner.close();
	}
	

	private int getInteger(Scanner scanner, int minNumReq) {
		int res = 0;
		try {
			while (true) {
				try {
					res = Integer.parseInt(scanner.next());
					if (res > minNumReq) {
						break;
					} else {
						System.out.println(ANSI_RED+"Please enter something greater than "+minNumReq+ANSI_RESET);
					}
				} catch (NumberFormatException ne) {
					System.out.print(ANSI_RED+"That's not a whole number.\n"+ANSI_RESET);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private List<PlayerModel> getPlayersName(Scanner scanner) {
		List<PlayerModel> playersList = new ArrayList<>();
		try {
			System.out.println("How many players will be playing the game ?");
			int numberOfPlayers = getInteger(scanner, 1);
			System.out.println("Please enter those "+numberOfPlayers+" player names.");
			for (int i = 0; i < numberOfPlayers; i++) {
				PlayerModel model = new PlayerModel();
				model.setPlayerName(scanner.next());
				playersList.add(model);
			}
			System.out.println(numberOfPlayers+" players added.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playersList;
	}
}
