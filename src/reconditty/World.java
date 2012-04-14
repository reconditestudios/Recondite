/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconditty;

import actors.Player;
import java.util.*;
import actors.*;

/**
 *
 * @author Zane-Gareth
 */
public class World {

    public static ArrayList floors = new ArrayList();
    public static Player player;

    public World() {
        floors.add(new Floor());
        player = new Player(getFirstRoom());
        getFirstRoom().addMonster("goblin");
        player.go(getFirstRoom());
    }

    public static void enemyTurn() {
        if (Reconditty.gameRunning && Reconditty.playerAlive) {
            for (int i = 0; i < activeMonsters().size(); i++) {
                if (!Reconditty.gameRunning || !Reconditty.playerAlive) {
                    break; //ends game more cleanly if player is dead etc.
                }
                ((Monster) activeMonsters().get(i)).turn();
            }
        }
    }
    
    public static ArrayList activeMonsters() {
        return player.currentFloor.monsters;
    }
    
    public static Room getFirstRoom() {
        return ((Floor) floors.get(0)).firstRoom;
    }
}
