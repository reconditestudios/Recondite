/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monsters;

/**
 *
 * @author Zane-Gareth
 */
public interface Monster {

    public int currentHealth = 0;
    public int currentAC = 0;

    public void turn();

    public void attack();

    public void getHurt(int damage);

    public Boolean isDead();
}
