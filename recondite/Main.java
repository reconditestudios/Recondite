package recondite;

import java.io.*;

/**
 *
 * @author Zane
 */
public class Main {
    
    static Boolean playerAlive = true;
    static String[] commandList = new String[1];
    static String command;
    
    private static void player(){
        int coordX = 0;
        int coordY = 0;
        
    }
    
    public static void main(String[] args) {
        
        //As long as the Player is alive, run the game.
        while(playerAlive) {
            
            //Inform Player of monsters/treasure/exits
        	
            System.out.println("");
            
            //Request Player command.
            System.out.println("What do you do? ");
            
            //Take Player command.
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                command = reader.readLine();
            }
            catch (IOException ioe) {
                System.out.println("An unexpected error has occurred.");
            }
            System.out.print(command);
            //Perform a command based on the input.
            for (int i=0; i<commandList.length; i++) {
                if (command != commandList[i]) {
                    System.out.println("\nPlease enter a valid command.\nType help to see a list of commands.");
                }
            }
            
            //Resolve combat.
            
            //Move player.
            
        }
        
        System.out.println("You have died. Game over.");
        
    }
}