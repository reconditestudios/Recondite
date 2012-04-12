/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import actors.Item;
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import actors.*;
import reconditty.Reconditty;
import reconditty.Room;
import reconditty.World;

public class Player {

    //Internal Variables//
    private String[] commandList = {"spawn", "help", "attack", "defend", "cast", "wait", "health", "quit", "go north", "go west", "go east", "look"};
    private String[] shortCommands = {"a", "d", "c", "w", "h", "q", "g n", "g w", "g e", "l"};
    private Random random = new Random();
    private BufferedReader reader;
    private boolean restartTurn = false;
    private boolean guarding;
    public Room currentRoom;
    //Stats. All placeholder values for now.//
    public Item[] inventory = {};
    public int maxHealth = 10;
    public int maxMana = 5;
    public int damage = 3;
    public int baseAC = 12;
    public int attackModifier = 5;
    public int turnsSinceGuard;
    public int currentAC;
    public int currentHealth;
    public int currentMana;

    public Player(Room firstRoom) {
        currentRoom = firstRoom;
        turnsSinceGuard = 0;
        currentHealth = maxHealth;
        currentMana = maxMana;
        currentAC = baseAC;
    }

    public void turn() {
        restartTurn = false;
        //Get a command from the player.
        String command = getCommand();
        doTheThing(command);

        //Deals with defending timing out.
        if (!restartTurn) {
            if (guarding && turnsSinceGuard > 1) {
                currentAC -= 5;
                guarding = false;
            }
            turnsSinceGuard++;
        }
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

    private void doTheThing(String command) {

        System.out.println(""); // for spacing

        //Translate the command into a method call.
        if (command.equals("help")) {
            help();
            restartTurn = true;
        } else if (command.equals("go north") || command.equals("g n")) {
            go(currentRoom.adjRooms[0]);
        } else if (command.equals("go east") || command.equals("g e")) {
            go(currentRoom.adjRooms[1]);
        } else if (command.equals("go west") || command.equals("g w")) {
            if (currentRoom.equals(World.firstRoom)) {
                System.out.println("There is no exit to the west.");
            } else {
                go(currentRoom.adjRooms[2]); //TODO: Implement better directional system.   
            }
        } else if (command.equals("look") || command.equals("l")) {
            look();
            restartTurn = true;
            turn();
        } else if (command.equals("attack") || command.equals("a")) {
            attack();   //TODO: Implement target choosing.
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
            currentRoom.addMonster("goblin");
            System.out.println("A goblin appears! \n");
            //TODO: Implement a method to spawn different monster types.
            restartTurn = true;
            turn();
        } else if (command.equals("end")) {
            Reconditty.gameRunning = false;
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
            boolean crit = (roll == 20); //if a 20 is rolled, it's a crit

            int tempDamage = damage;
            if (crit) { //If it's a crit, double damage is dealt
                tempDamage = 2 * damage;
            }
            target.getAttacked(roll + attackModifier, tempDamage, crit);

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

    private void go(Room targetRoom) {
        targetRoom.genAdjRooms();
        currentRoom = targetRoom;
        System.out.println("You enter the room.");
        System.out.println("There are " + currentRoom.monsters.size() + " monsters here.");
        //TODO: Add code to set up the room.
    }

    private void look() {
        System.out.println("There are " + currentRoom.monsters.size() + " monsters here.");
        //TODO: Add room exits to look().
        System.out.println(currentRoom.adjRooms);
    }
}