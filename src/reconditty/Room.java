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
    public Room[] adjRooms;
    public Room roomAbove;
    public Room roomBelow;
    private Random random = new Random();
    public boolean enteredByPlayer;
    public boolean hasDownLadder;
    public boolean hasUpLadder;

    public Room(Room parentRoom, boolean isFirstRoom) {
        int chance = random.nextInt(10);
        if (!isFirstRoom) {
            hasDownLadder = true;
            World.floors.add(new Floor(this));
            roomBelow = (World.lowestFloor()).upLadderRoom;
        }
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
            //TODO: Add code to generate room contents.
            enteredByPlayer = true;
        }
        System.out.println("There are " + monsters.size() + " monsters here.");
        System.out.println("There are " + World.player.currentRoom.adjRooms.length + " exits here.");
        if (hasDownLadder) {
            System.out.println("The ladder down is here.");
        }
        if (hasUpLadder) {
            System.out.println("The ladder up is here.");
        }
    }
}
