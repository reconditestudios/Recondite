/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconditty;

import monsters.*;
import java.io.*;
import java.util.Arrays;

public class Combat {
	public boolean playerAlive = true;
	public int monstersAlive = 0;
	public Goblin goblin;
	public Monster[] monsters = {goblin};
	public String[] commandList = {"attack", "defend", "spell"};
	public String command;
	static BufferedReader reader;
	
	/**
	 * As long as the player is alive {
	 * 	Take player input;
	 * 	Run command based on input;
	 * 	Enemies respond;
	 * }
	 **/

	public void Combat() {
	}

	public void startFight() {
		playerAlive = true;
		while (playerAlive && monstersAlive > 0){
			playerTurn();
			enemyTurn();
		}
	}

	/**
	 * Make a given number of monsters.
	 * @param monsterQuantity - The number of monsters desired.
	 */
	public void addMonster(String monsterType) {
		if (monsterType.equals("goblin")) {
			Goblin goblin = null;
			monsters[monstersAlive] = goblin;
		}
		monstersAlive++;
	}

	/**
	 * Requests player input and performs an action based on the command.
	 */
	public void playerTurn() {
		
		//Set up reader to take input and specify that we haven't received a command.
		reader = new BufferedReader(new InputStreamReader(System.in));
		Boolean commandReceived = false;			

		//Take a player command.
		while(commandReceived == false) {
			command = null;
			
			System.out.println("What do you do? ");
			try {
				command = reader.readLine();
			} catch(IOException IOE) {
				System.out.println("An unexpected error occurred.");
			}
			
			if (Arrays.asList(commandList).contains(command)) {
				commandReceived = true;
			} else {
				System.out.println("Enter 'help' to see a list of valid commands.");
			}
		}
		
		//Translate the command into a method call. Is there a better way to do this?
		if(command.equals("attack")) {
			Player.attack(goblin);
			/*for (int i = 0; i < monstersAlive; i++) {
				if (!monsters[i].isDead()) {
					Player.attack(monsters[i]);
				}
			}*/
		} else if(command.equals("defend")) {
			Player.defend();
		}
		//Add more commands.
	}
	
	public void enemyTurn() {
		for (int i = 0; i < monstersAlive; i++) {
			monsters[i].attack();
		}
	}
}