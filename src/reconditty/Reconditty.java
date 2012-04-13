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
        gameRunning = true;
        world = new World();
        playerAlive = true;
        while (playerAlive && gameRunning) {
            World.player.turn();    //run player's turn
            World.enemyTurn();      //for each enemy, run their turn
            System.out.println(""); //for spacing
        }
    }
}
