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

    public static ArrayList monsters = new ArrayList();
    public static Player player;
    public static Room firstRoom;

    public World() {
        firstRoom = new Room(null);
        firstRoom.addMonster("goblin");
        firstRoom.getEntered();
        player = new Player(firstRoom);
        System.out.println("There are " + firstRoom.monsters.size() + " goblins here.");
    }

    public static void enemyTurn() {
        if (Reconditty.gameRunning && Reconditty.playerAlive) {
            for (int i = 0; i < monsters.size(); i++) {
                if (!Reconditty.gameRunning || !Reconditty.playerAlive) {
                    break; //ends game more cleanly if player is dead etc.
                }
                ((Monster) monsters.get(i)).turn();
            }
        }
    }
}
