/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconditty;

import java.util.ArrayList;
import java.util.Random;
import actors.*;

/**
 *
 * @author Zane-Gareth
 */
public class Room {
    public ArrayList monsters = new ArrayList();
    private Random random = new Random();
    public Room[] adjRooms;
    public boolean entered;
    
    public Room(Room parentRoom) {
        //Decide how many adjacent rooms there are (1 to 3)
        int exits = random.nextInt(3) + 1;
        adjRooms = new Room[exits];
        //The 'parent room' will always be the last room in the list.
        adjRooms[adjRooms.length - 1] = (parentRoom);
        entered = false;
    }
    
    public void genAdjRooms() {
        for (int i = 0; i < adjRooms.length-1; i++) {
            adjRooms[i] = new Room(this);
        }
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
