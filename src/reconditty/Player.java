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
    //TODO: Add shortenings of all the commands. Help = h, etc.
    private String[] commandList = {"spawn", "help", "attack", "defend", "cast", "wait", "health", "end"};
    private String[] shortCommands = {"a", "d", "c", "w", "h",};
    private Random random = new Random();
    private BufferedReader reader;
    private boolean restartTurn = false;
    private boolean guarding;
    public Room currentRoom;
    //Stats. All placeholder values for now.//
    public int maxHealth = 10;
    public int maxMana = 5;
    public int speed = 10;
    public int damage = 3;
    public int baseAC = 12;
    public int attackModifier = 2;
    public int turnsSinceGuard;
    public int currentAC;
    public int currentHealth;
    public int currentMana;
    public int currentDamage;

    /**************************************/
    public Player() {
        turnsSinceGuard = 0;
        currentHealth = maxHealth;
        currentDamage = damage;
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
        restartTurn = false;
        //Get a command from the player.
        String command = getCommand();

        //Translate the command into a method call.
        if (command.equals("help")) {
            help();
            restartTurn = true;
        } else if (command.equals("attack") || command.equals("a")) {
            attack();
        } else if (command.equals("defend") || command.equals("d")) {
            defend();
        } else if (command.equals("cast") || command.equals("c")) {
            castSpell();
        } else if (command.equals("wait") || command.equals("w")) {
            // do nothing
        } else if (command.equals("health") || command.equals("h")) {
            System.out.println(currentHealth + "\n");
            restartTurn = true;
            turn();
        } else if (command.equals("spawn")) {
            World.addMonster(new Goblin(13));
        } else if (command.equals("end")) {
            Reconditty.gameRunning = false;
        }
        //TODO: Add more player commands.

        //Deals with defending timing out.
        if (!restartTurn) {
            if (guarding && turnsSinceGuard > 1) {
                currentAC -= 5;
                guarding = false;
            }
            turnsSinceGuard++;
        }
    }

    /* "Rolls" a random number from 1-20, adds any attack modifier,
     * and tests to see if the result is higher than the target AC.
     * If it is, deals damage.
     * @param Target.
     */
    private void attack() {
        try {
            Monster target = (Monster) World.monsters.get(0);
            int roll = (random.nextInt(20) + 1); //roll to hit

            //if it hits, deal damage
            if (roll + attackModifier >= target.currentAC || roll == 20) {
                //Critical Strike//
                if (roll == 20) {
                    damage *= 2;
                }
                System.out.println("You hit and deal " + damage + " points of damage.");
                target.getHurt(damage);
            } else {
                System.out.println("You miss.");
            }
            if (roll == 20) {
                damage /= 2;
            }
        } catch (IndexOutOfBoundsException dex) {
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
        turn();
    }

    private void castSpell() {
        if (currentMana >= 2) {
            Monster target = (Monster) World.monsters.get(0);
            target.getHurt(2);
            System.out.println("You deal 2 damage.");
        }
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
        if (currentHealth <= 0) {
            return true;
        }
        return false;
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

            if (Arrays.asList(commandList).contains(command)
                    || Arrays.asList(shortCommands).contains(command)) {
                commanded = true;
            } else {
                System.out.println("Type 'help' to see a list of valid commands. \n");
            }
        }

        return command;
    }
}
