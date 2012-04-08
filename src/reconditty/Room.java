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
    public Room[] adjacentRooms;

    public Room() {
    }

    public void genAdjRooms() {
    }

    public Monster[] listMonsters() {
        Monster[] monsters = {};
        return monsters;
    }

    /**
     * Make a given number of monsters.
     */
    public void addMonster(Goblin newGoblin) {
        newGoblin.index = monsters.size();
        monsters.add(newGoblin);
    }
}
