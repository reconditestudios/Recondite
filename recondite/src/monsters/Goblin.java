package monsters;

import java.util.Random;
import engine.*;

public class Goblin implements Monster {
	//Placeholder values for now.
	public static int maxHealth = 10;
	public static int speed = 2;
	public static int damage = 2;
	public static int baseAC = 10;
	public static int attackModifier = 1;

	public static int currentHealth = 10;
	public static int currentAC = 10;

	static Random random = new Random();

	public Goblin() {
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
		System.out.println(attack);

		if (attack > Player.currentAC) {
			Player.getHurt(damage);
			System.out.println("The goblin hit and dealt " + damage + " points of damage.");
			
			if (Player.isDead() == true) {
				System.out.println("You die.");
				Combat.playerAlive = false;
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
}
