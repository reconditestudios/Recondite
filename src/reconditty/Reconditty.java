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
    public static Player theDude;
    public static World hyrule;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Reconditty game = new Reconditty();
        game.start();
        hyrule = new World();
        theDude = new Player();
        Combat fight = new Combat();
        fight.startFight();
    }

    public static void start() {
    }
}
