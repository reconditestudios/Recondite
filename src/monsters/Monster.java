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
    
    public int currentAC;
    
    public Monster(int baseAC) {
        currentAC = baseAC;
    }

    public abstract void turn();

    public abstract void attack();

    public abstract void getHurt(int damage);

    public abstract Boolean isDead();

    public abstract void die();
}
