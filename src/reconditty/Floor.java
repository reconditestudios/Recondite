/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconditty;

import java.util.ArrayList;

/**
 *
 * @author Zane-Gareth
 */
public class Floor {
    
    public static ArrayList monsters = new ArrayList();
    public Room upLadderRoom;
    public int depth;
    
    public Floor(Room roomLeadingHere) {
        depth = World.floors.size() + 1;
        if (depth == 1) {
            upLadderRoom = new Room(null,true);
        } else {
            upLadderRoom = new Room(roomLeadingHere,true);
        }
        upLadderRoom.roomAbove = roomLeadingHere;
        upLadderRoom.hasUpLadder = true;
    }
}
