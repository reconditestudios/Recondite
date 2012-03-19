/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconditty;
import java.util.Random;
import monsters.*;

public class Player {
	//All placeholder values for now.
	public int maxHealth = 100;
	public int maxMana = 100;
	public int speed = 10;
	public int damage = 10;
	public int baseAC = 12;
	public int attackModifier = 3;

	public int currentHealth;
	public int currentMana;
	public int currentAC;

	Random random = new Random();

	public Player() {
		currentHealth = maxHealth;
		currentMana = maxMana;
		currentAC = baseAC;
	}

	/**
	 * "Rolls" a random number from 1-20, adds any attack modifier,
	 * and tests to see if the result is higher than the target AC.
	 * If it is, deals damage.
	 * @param Target.
	 */
	public void attack(Monster target) {
		int attack = (random.nextInt(20) + 1) + attackModifier;
		System.out.println(attack);
		
		if (attack > target.currentAC) {
			target.getHurt(damage);
			System.out.println("You hit and deal " + damage + " points of damage.");
			
			if (target.isDead() == true) {
				System.out.println("It dies.");
				Combat.monstersAlive -= 1;
			}
		} else {
			System.out.println("You missed.");
		}
	}

	/**
	 * Hurts the player by a given amount.
	 * @param Damage to be dealt.
	 */
	public void getHurt(int damage) {
		currentHealth -= damage;
	}

	/**
	 * Checks if the player's health has dropped below 0.
	 */
	public Boolean isDead() {
		Boolean isDead = false;
		if (currentHealth < 0) {
			isDead = true;
		}
		return isDead;
	}

	public void defend() {
		currentAC += 5;
	}
	/**
	 * Levels up the player character; increases said character's
	 * stats.
	 */
	public void levelUp() {
		//Placeholder values for now.
		maxHealth += 10;
		maxMana += 10;
		speed += 1;
		damage +=1;
	}
}
