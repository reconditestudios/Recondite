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
    public Room firstRoom;

    public World() {
        firstRoom = new Room();
        player = new Player(firstRoom);
        Goblin goblin = new Goblin(0,0,firstRoom);
        monsters.add(goblin);
        firstRoom.monsters.add(goblin);
    }

    public static void enemyTurn() {
        if (Reconditty.gameRunning) {      //quits game more cleanly
            for (int i = 0; i < monsters.size(); i++) {
                ((Monster) monsters.get(i)).turn();
            }
        }
    }
}
