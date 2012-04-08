/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monsters;

import reconditty.*;
import java.util.Random;
import java.lang.*;

/**
 *
 * @author Zane-Gareth
 */
public class Goblin extends Monster {

    //Local variables.//
    private Random random = new Random();
    //Stats. Placeholder values for now.//
    public int attackModifier = 1;
    public int currentHealth = 15;
    public int maxHealth = 10;
    public int baseAC = 13;
    public int damage = 2;
    public int speed = 2;
    
    public int index;

    public Goblin(int baseAC) {
        super(baseAC);
        currentHealth = maxHealth;
    }

    public void turn() {
        attack();
    }

    /**
     * "Rolls" a random number from 1-20, adds any attack modifier,
     * and tests to see if the result is higher than the target AC.
     * If it is, deals damage.
     * Also checks to see if the player is killed, in which case it
     * informs the player.
     */
    public void attack() {
        int attack = (random.nextInt(20) + 1) + attackModifier;

        if (attack > World.player.currentAC) {
            World.player.getHurt(damage);
            System.out.println("The goblin hit and dealt " + damage + " points of damage.");

            if (World.player.isDead() == true) {
                World.player.die();
            }
        } else {
            System.out.println("The goblin missed.");
        }
    }

    /**
     * Hurts the goblin by a given amount.
     * @param Damage to be dealt.
     */
    public void getHurt(int incomingDamage) {
        currentHealth -= incomingDamage;
        if (currentHealth <= 0) {
            die();
        }
    }

    /**
     * Checks if the goblin's health has dropped below 0.
     */
    public Boolean isDead() {
        Boolean isDead = false;
        if (currentHealth < 0) {
            isDead = true;
        }
        return isDead;
    }
    
    public void die() {
        World.monsters.remove(index);
        System.out.println("The goblin dies.");
        if (World.monsters.size() == 0) {
            Reconditty.gameRunning = false;
        }
    }
}
