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
    public boolean enteredByPlayer;

    public Room(Room parentRoom, boolean isFirstRoom) {
        //Decide how many adjacent rooms there are (1 to 3)
        int exits;
        if (isFirstRoom) {
            exits = random.nextInt(2) + 2;
        } else {
            exits = random.nextInt(3) + 1;
        }
        adjRooms = new Room[exits];
        //The 'parent room' will always be the last room in the list.
        adjRooms[adjRooms.length - 1] = (parentRoom);
        enteredByPlayer = false;
    }

    public void genAdjRooms() {
        for (int i = 0; i < adjRooms.length - 1; i++) {
            adjRooms[i] = new Room(this, false);
        }
    }

    public void addMonster(String monsterType) {
        Monster monster = null;
        if (monsterType.equals("goblin")) {
            monster = new Goblin(World.activeMonsters().size(), monsters.size(), this);
        }
        World.activeMonsters().add(monster);
        monsters.add(monster);
    }

    /* If the player has been here before, tells him.
     * Else, sets up the room and generates adjacent rooms.
     */
    public void getEntered() {
        if (enteredByPlayer) {
            System.out.println("This room looks familiar.");
        } else {
            this.genAdjRooms();
            //TODO: Set up contents of room
            enteredByPlayer = true;
        }
        System.out.println("There are " + World.activeMonsters().size() + " monsters here.");
        System.out.println("There are " + World.player.currentRoom.adjRooms.length + " exits here.");
    }
}
