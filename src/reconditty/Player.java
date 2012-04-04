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
    private boolean guarding;
    BufferedReader reader;
    Room currentRoom;
    //Stats. All placeholder values for now.//
    public int maxHealth = 100;
    public int maxMana = 100;
    public int speed = 10;
    public int damage = 10;
    public int baseAC = 12;
    public int attackModifier = 3;
    public int turnsSinceGuard;
    public int currentAC;
    int currentHealth;
    int currentMana;

    /**************************************/
    public Player() {
        turnsSinceGuard = 0;
        currentHealth = maxHealth;
        currentMana = maxMana;
        currentAC = baseAC;
    }

    /* Levels up the player character; increases his stats.
     */
    public void levelUp() {
        //Placeholder values for now.
        maxHealth += 10;
        maxMana += 10;
        speed += 1;
        damage += 1;
    }

    public void turn() {
        //Get a command from the player.
        String command = getCommand();

        //Translate the command into a method call.
        if (command.equals("attack")) {
            attack(World.monsters[0]);
        } else if (command.equals("defend")) {
            defend();
        } else if (command.equals("cast")) {
            castSpell();
        } else if (command.equals("help")) {
            help();
        }
        //TODO: Add more player commands.

        if (guarding && turnsSinceGuard > 1) {
            currentAC -= 5;
            guarding = false;
        }
        turnsSinceGuard++;
    }

    /* "Rolls" a random number from 1-20, adds any attack modifier,
     * and tests to see if the result is higher than the target AC.
     * If it is, deals damage.
     * @param Target.
     */
    private void attack(Monster target) {
        if (World.monsters.length > 0) {
            int roll = (random.nextInt(20) + 1) + attackModifier;

            if (roll > target.currentAC || roll == 20) {
                //Critical Strike//
                if (roll == 20) {
                    damage *= 2;
                }

                target.getHurt(damage);
                System.out.println("You hit and deal " + damage + " points of damage.");

                if (target.isDead() == true) {
                    target.die();
                    //TODO: Add a way to remove dead monsters from the list.
                }
            } else {
                System.out.println("You miss.");
            }
            if (roll == 20) {
                damage /= 2;
            }
        } else {
            System.out.println("There is nothing to attack.");
        }
    }

    /* Temporarily increases the player's AC by a set amount. */
    private void defend() {
        if (!guarding) {
            guarding = true;
            currentAC += 5;
        }
        turnsSinceGuard = 0;
    }

    private void help() {
        for (int i = 0; i < commandList.length; i++) {
            System.out.println(commandList[i]);
        }
    }

    private void castSpell() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /* Hurts the player by a given amount.
     * @param Damage to be dealt.
     */
    public void getHurt(int damage) {
        currentHealth -= damage;
    }

    /* Checks if the player's health has dropped below 0.
     */
    public Boolean isDead() {
        Boolean isDead = false;
        if (currentHealth < 0) {
            isDead = true;
        }
        return isDead;
    }

    /* Deals with player death.
     */
    public void die() {
        System.out.println("You die.");
        Reconditty.playerAlive = false;
    }

    private String getCommand() {
        String command = "";
        //Set up reader to take input, specify that we haven't received a command.
        reader = new BufferedReader(new InputStreamReader(System.in));
        Boolean commanded = false;

        //Take a player command.
        while (commanded == false) {
            System.out.println("What do you do? ");
            try {
                command = reader.readLine();
            } catch (IOException IOE) {
                System.out.println("An input error occurred.");
            }

            if (Arrays.asList(commandList).contains(command)) {
                commanded = true;
            } else {
                System.out.println("Type 'help' to see a list of valid commands.");
            }
        }

        return command;
    }
}
