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

    static boolean playerAlive;
    public static World world;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Reconditty game = new Reconditty();
        game.start();
    }

    public void start() {
        world = new World();
        playerAlive = true;
        while (playerAlive) {
            world.player.turn();
            //world.enemyTurn();
        }
    }
}
