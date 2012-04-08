/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monsters;

/**
 *
 * @author Zane-Gareth
 */
public abstract class Monster {

    public abstract void turn();

    public abstract void attack();

    public abstract void getHurt(int damage);

    public abstract Boolean isDead();

    public abstract void die();

    public void getAttacked(int roll, int damage, boolean criticalHit) {
    }
}
