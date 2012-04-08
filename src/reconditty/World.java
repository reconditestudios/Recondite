/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconditty;

import java.util.*;
import monsters.*;

/**
 *
 * @author Zane-Gareth
 */
public class World {

    public static ArrayList monsters = new ArrayList();
    public static Player player;
    Room firstRoom;

    public World() {
        firstRoom = new Room();
        player = new Player();
        player.currentRoom = firstRoom;
        monsters.add(new Goblin(13));
    }

    public static void genAdjRooms(Room curRoom) {
    }

    public static void enemyTurn() {
        for (int i=0; i < monsters.size(); i++) {
            ((Monster)monsters.get(i)).turn();
        }
    }

    /**
     * Make a given number of monsters.
     */
    public static void addMonster(Goblin newGoblin) {
        newGoblin.index = monsters.size();
        monsters.add(newGoblin);
    }
}
