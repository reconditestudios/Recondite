package engine;

import java.io.*;
import java.util.Arrays;
import monsters.*;

public class Combat {
	public static Boolean playerAlive = true;
	public static int monstersAlive = 1;
	public static Goblin goblinExample;
	public static Monster[] monsters = {goblinExample};
	public static String[] commandList = {"attack", "defend", "spell"};
	public static String command = null;
	static BufferedReader reader;
	
	/**
	 * As long as the player is alive {
	 * 	Take player input;
	 * 	Run command based on input;
	 * 	Enemies respond;
	 * }
	 **/

	public static void Combat() {
	}

	public static void startFight() {
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
	public static void addMonster(String monsterType) {
		if (monsterType.equals("goblin")) {
			Goblin goblin = null;
			monsters[monstersAlive] = goblin;
		}
		monstersAlive++;
	}

	/**
	 * Requests player input and performs an action based on the command.
	 */
	public static void playerTurn() {
		
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
			Player.attack(goblinExample);
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
	
	public static void enemyTurn() {
		for (int i = 0; i < monstersAlive; i++) {
			monsters[i].attack();
		}
	}
}
