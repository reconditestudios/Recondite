/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconditty;

import monsters.*;
import Reconditty.*;
import java.io.*;
import java.util.Arrays;

public class Combat {

    public static boolean playerAlive = true;
    public static int monstersAlive = 1;
    public static Player player = new Player();
    public static Goblin goblin = new Goblin();
    public static Monster[] monsters = {goblin};
    static BufferedReader reader;

    /**
     * As long as the player is alive {
     * 	Take player input;
     * 	Run command based on input;
     * 	Enemies respond;
     * }
     **/
    public Combat() {
        playerAlive = true;
        while (playerAlive && monstersAlive > 0) {
            player.turn();
            enemyTurn();
        }
    }
    
    public void startFight() {
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

    public void enemyTurn() {
        for (int i = 0; i < monstersAlive; i++) {
            monsters[i].turn();
        }
    }
}