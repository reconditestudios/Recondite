/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconditty;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import monsters.*;

public class Player {
    //Internal Variables//
    String[] commandList = {"attack", "defend", "spell"};
    Random random = new Random();
    BufferedReader reader;
    String command;
    //Stats. All placeholder values for now.//
    public int maxHealth = 100;
    public int maxMana = 100;
    public int speed = 10;
    public int damage = 10;
    public int baseAC = 12;
    public int attackModifier = 3;
    public int currentAC;
    int currentHealth;
    int currentMana;
    /**************************************/

    public Player() {
        currentHealth = maxHealth;
        currentMana = maxMana;
        currentAC = baseAC;
    }

    /* "Rolls" a random number from 1-20, adds any attack modifier,
     * and tests to see if the result is higher than the target AC.
     * If it is, deals damage.
     * @param Target.
     */
    public void attack(Monster target) {
        int roll = (random.nextInt(20) + 1) + attackModifier;

        if (roll > target.currentAC || roll == 20) {
            //Critical Strike//
            if (roll == 20) {
                damage *= 2;
            }
            
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

    public void defend() {
        currentAC += 5;
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

    /**
     * Levels up the player character; increases said character's
     * stats.
     */
    public void levelUp() {
        //Placeholder values for now.
        maxHealth += 10;
        maxMana += 10;
        speed += 1;
        damage += 1;
    }

    public void turn() {
        //Set up reader to take input, specify that we haven't received a command.
        reader = new BufferedReader(new InputStreamReader(System.in));
        Boolean commanded = false;

        //Take a player command.
        while (commanded == false) {
            System.out.println("What do you do? ");
            try {
                command = reader.readLine();
            } catch (IOException IOE) {
                System.out.println("An unexpected error occurred.");
            }

            if (Arrays.asList(commandList).contains(command)) {
                commanded = true;
            } else {
                System.out.println("Enter 'help' to see a list of valid commands.");
            }
        }

        //Translate the command into a method call. Is there a better way to do this?
        if (command.equals("attack")) {
            attack(Combat.monsters[0]);
            /*for (int i = 0; i < monstersAlive; i++) {
            if (!monsters[i].isDead()) {
            Player.attack(monsters[i]);
            }
            }*/
        } else if (command.equals("defend")) {
            defend();
        }
        //Add more commands.
    }
}
