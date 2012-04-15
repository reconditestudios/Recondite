/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconditty;

/**
 *
 * @author Zane-Gareth
 */
public class Reconditty {

    public static boolean gameRunning;
    public static boolean playerAlive;
    public static World world;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Reconditty game = new Reconditty();
        game.start();
    }

    public void start() {
        System.out.println("You descend into the dungeon.\n");
        
        gameRunning = true;
        world = new World();
        playerAlive = true;
        
        while (playerAlive && gameRunning) {
            World.player.turn();    //run player's turn
            World.enemyTurn();      //run each enemy's turn
            System.out.println(""); //for spacing
        }
    }
}
