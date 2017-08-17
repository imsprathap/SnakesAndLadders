package org.target.snakesAndLadders;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.target.snakesAndLadders.model.GameModel;
import org.target.snakesAndLadders.model.PlayerModel;
import org.target.snakesAndLadders.model.PropertyModel;

public class PlayGame {

	private static final String NEED_TO_PLAY = "Do you want to play ? Y or N  > ";
	private static final String THROW_DICE = "Do you want throw the Dice ? Y or N  > ";
	
	public boolean startGame(GameModel gameModel, Scanner scanner){
		boolean isContinued = userInputToPlay(scanner, NEED_TO_PLAY);
		if(isContinued){
			System.out.println("\n*************************************\n Game starts here !!!! \n*************************************");
			List<PlayerModel> playerList = initGame(gameModel);
			printStatus(playerList);
			startGame(gameModel, scanner, playerList);
		}else{
			System.out.println("See You...Bye..!!");
		}
		return true;
	}
	
	public List<PlayerModel> initGame(GameModel gameModel){
		int initialHelath = getHealth(gameModel.getnBlocks());
		int initialPosition = 1;
		List<PlayerModel> playersList = gameModel.getPlayersList();
		for (PlayerModel playerModel : playersList) {
			playerModel.setCurrentPos(initialPosition);
			playerModel.setHealth(initialHelath);
		}
		return playersList;
	}

	private int getHealth(int nBlocks) {
		int initialHealth = 0;
		if(nBlocks != 0){
			initialHealth = nBlocks / 3;
		}
		return initialHealth;
	}
	
	private void startGame(GameModel gameModel, Scanner scanner, List<PlayerModel> playerList){
		boolean isGameOver = false;
		while(!isGameOver){
			boolean isContinued = userInputToPlay(scanner, THROW_DICE);
			if(isContinued){
				isGameOver = throwDiceForEachPlayer(playerList, gameModel, scanner);
			}else{
				System.out.println("See You...Bye..!!");
				break;
			}
		}
	}
	
	private boolean throwDiceForEachPlayer(List<PlayerModel> playerList, GameModel gameModel, Scanner scanner) {
		boolean isGameOver = false;
		for (PlayerModel playerModel : playerList) {
			reduceHealth(playerModel, gameModel);
			int diceValue = GameUtils.generateRandom(1, 6);
			System.out.println(playerModel.getPlayerName() + " got a dice value : " + diceValue);
			playerModel.setCurrentDiceValue(diceValue);
			if (diceValue != 0) {
				int updatedPosition = playerModel.getCurrentPos() + diceValue;
				if (updatedPosition < gameModel.getnBlocks()) {
					PlayerModel updatedPlayerModel = findNextPosition(playerModel, gameModel, scanner, updatedPosition, 0);
					if (updatedPlayerModel.getCurrentPos() == gameModel.getnBlocks()) {
						System.out.println("********Game Over**********\nCONGRATS !!!....\n"
								+ updatedPlayerModel.getPlayerName() + "...You Won!!!!\n*******************");
						isGameOver = true;
						break;
					}
				} else if (updatedPosition == gameModel.getnBlocks()) {
					System.out.println("********Game Over**********\nCONGRATS !!!....\n" + playerModel.getPlayerName()
							+ "...You Won!!!!\n*******************");
					isGameOver = true;
					break;
				} else {
					System.out.println("Sorry this dice value crosses max value.. Try again..!!");
				}
				handleHealth(playerModel, gameModel);
			}
			printStatus(playerList);
		}
		return isGameOver;
	}

	private void handleHealth(PlayerModel playerModel, GameModel gameModel) {
		int health = playerModel.getHealth();
		if(!(health > 0)){
			System.out.println("Sorry, Your health is Zero. Need to start from 1");
			playerModel.setCurrentPos(1);
			int initialHealth = getHealth(gameModel.getnBlocks());
			playerModel.setHealth(initialHealth);
		}
	}

	private void reduceHealth(PlayerModel playerModel, GameModel gameModel) {
		int health = playerModel.getHealth();
		if(health > 0){
			playerModel.setHealth(health-1);
		}
	}

	private PlayerModel findNextPosition(PlayerModel playerModel, GameModel gameModel, Scanner scanner, int updatedPosition, int recurssionValue) {
		try{

			Map<Integer, PropertyModel> gameModelMap = gameModel.getGameBoardMap();
			int diceValue = playerModel.getCurrentDiceValue();
			
			PropertyModel propertyModel = gameModelMap.get(updatedPosition);
			String property = propertyModel.getPropertyType();
			if(null != property && !Constants.EMPTY.equalsIgnoreCase(property)){
				if(property.equalsIgnoreCase(Constants.LADDER)){
					updatedPosition = propertyModel.getNextPosition();
					playerModel.setCurrentPos(propertyModel.getNextPosition());
					System.out.println("Congrats !! You got Ladder ");
					System.out.println("Your new position is : "+ playerModel.getCurrentPos() );
				}else if(property.equalsIgnoreCase(Constants.SNAKE)){
					updatedPosition = propertyModel.getNextPosition();
					playerModel.setCurrentPos(propertyModel.getNextPosition());
					System.out.println("Ohh ohh !! You got Snake ");
					System.out.println("Your new position is : "+ playerModel.getCurrentPos() );
				}else if(property.equalsIgnoreCase(Constants.PITSTOP)){
					playerModel.setCurrentPos(updatedPosition);
					System.out.println("Congrats !! You got Pit stop ");
					System.out.println("Your new position is : "+ playerModel.getCurrentPos() );
					int additionalHealth = getHealth(gameModel.getnBlocks()-playerModel.getCurrentPos());
					if(additionalHealth > 0)
						playerModel.setHealth(playerModel.getHealth()+additionalHealth);
				}else if(property.equalsIgnoreCase(Constants.SPRING)){
					double newValue = updatedPosition - diceValue; 
					if(newValue < 1)
						updatedPosition = 1;
					else
						updatedPosition = (int)newValue;
					playerModel.setCurrentPos(updatedPosition);
					System.out.println("Ohh ohh !! You got Spring ");
					System.out.println("Your new position is : "+ playerModel.getCurrentPos() );
				}else if(property.equalsIgnoreCase(Constants.TRAMPOLINE)){
					double newValue = updatedPosition + diceValue; 
					if(newValue <= gameModel.getnBlocks())
						updatedPosition = (int)newValue;
					else
						System.out.println("Sorry this Trampoline crosses max value.. Try again..!!");
					playerModel.setCurrentPos(updatedPosition);
					System.out.println("Congrats !! You got Trampolines ");
					System.out.println("Your new position is : "+ playerModel.getCurrentPos() );
				}
				propertyModel = gameModelMap.get(playerModel.getCurrentPos());
				property = propertyModel.getPropertyType();
				if(null != property && !Constants.EMPTY.equalsIgnoreCase(property) && !Constants.PITSTOP.equalsIgnoreCase(property) && updatedPosition <= gameModel.getnBlocks()){
					if(recurssionValue < 25){
						recurssionValue = recurssionValue+1;
						findNextPosition(playerModel, gameModel, scanner, updatedPosition, recurssionValue);
					}
					else{
						System.out.println("Warning !!! I see some infinite recurssion here, lets stop it here");
					}
				}
			}else{
				System.out.println("You currently landed on empty square..!!");
			}
		}catch(StackOverflowError e){
			System.out.println("Recusive"+e);
		}
		return playerModel;
	}

	private void printStatus(List<PlayerModel> playerList) {
		System.out.println("\n-------------------------\n");
		for (PlayerModel playerModel : playerList) {
			System.out.println("Player Name : "+playerModel.getPlayerName());
			System.out.println("Player Position : "+playerModel.getCurrentPos());
			System.out.println("Player Health : "+playerModel.getHealth());
			System.out.println("\n-------------------------");
		}
		System.out.println("\n-------------------------\n");
	}
	
	public boolean userInputToPlay(Scanner scanner, String message){
		System.out.println(message);
		boolean isContinued = false;
		while(true){
			String decision = scanner.next();
			if(null != decision && !"".equalsIgnoreCase(decision) && (decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("N"))){
				if(decision.equalsIgnoreCase("N")){
					isContinued = false;
				}else{
					isContinued = true;
				}
				break;
			}else{
				System.out.println("Please Enter Y / N");
			}
		}
		return isContinued;
	}
	
}
