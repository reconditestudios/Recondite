/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconditty;

import java.util.ArrayList;
import monsters.*;

/**
 *
 * @author Zane-Gareth
 */
public class Room {

    public ArrayList monsters = new ArrayList();
    public Room[] adjacentRooms = new Room[3];
    public boolean entered;

    public Room(Room parentRoom) {
        adjacentRooms[2] = parentRoom;
        entered = false;
    }

    public void genAdjRooms() {
        adjacentRooms = new Room[3];
        Room room1 = new Room(this);
        Room room2 = new Room(this);
        adjacentRooms[0] = room1;
        adjacentRooms[1] = room2;
        //TODO: Add element of randomness in number of adjacent rooms.
    }

    public void addMonster(String monsterType) {
        Monster monster = null;
        if (monsterType.equals("goblin")) {
            monster = new Goblin(World.monsters.size(), monsters.size(), this);
        }
        World.monsters.add(monster);
        monsters.add(monster);
    }
}
