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
            world.player.turn();
            if (gameRunning) {      //quits game more cleanly
                world.enemyTurn();
            }
            System.out.println(""); //for spacing
        }
    }
}
