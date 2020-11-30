/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstproject;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Chelsea
 */
public class FirstProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean userActive = true;
        int totalScore = 300;
        int itrCount = 0;
        int reward = 0;
        char direction = 0;
        int x = 0;
        int y = 0;
        
        while(userActive == true) {
            displayInfo(x, y, itrCount, totalScore);
            switch(inputDirection()) {
                case 'u':
                    direction = 'u';
                    itrCount++;
                    totalScore = totalScore - 10;
                    if(doesExceed(x, y, direction)) {
                        System.out.println("Exceed boundary, -2000 damage applied");
                        totalScore -= 2000;
                        totalScore += punishOrMercy(direction, reward());
                        if(isGameOver(x, y, totalScore, itrCount)) {
                            evaluation(totalScore);
                            userActive = false;
                        }
                    }
                    else {
                        y++;
                        totalScore += punishOrMercy(direction, reward());
                        if(isGameOver(x, y, totalScore, itrCount)) {
                            evaluation(totalScore);
                            userActive = false;
                        }
                    }
                    break;
                case 'd':
                    direction = 'd';
                    itrCount++;
                    totalScore -= 50;
                    if(doesExceed(x, y, direction)) {
                        System.out.println("Exceed boundary, -2000 damage applied");
                        totalScore -= 2000;
                        totalScore += reward();
                        if(isGameOver(x, y, totalScore, itrCount)) {
                            evaluation(totalScore);
                            userActive = false;
                        }
                    }
                    else {
                        y--;
                        totalScore += reward();
                        if(isGameOver(x, y, totalScore, itrCount)) {
                            evaluation(totalScore);
                            userActive = false;
                        }
                    }
                    break;
                case 'r':
                    direction = 'r';
                    itrCount++;
                    totalScore -= 50;
                    if(doesExceed(x, y, direction)) {
                        System.out.println("Exceed boundary, -2000 damage applied");
                        totalScore -= 2000;
                        totalScore += reward();
                        if(isGameOver(x, y, totalScore, itrCount)) {
                            evaluation(totalScore);
                            userActive = false;
                        }
                    }
                    else {
                        x++;
                        totalScore += reward();
                        if(isGameOver(x, y, totalScore, itrCount)) {
                            evaluation(totalScore);
                            userActive = false;
                        }
                    }
                    break;
                case 'l':
                    direction = 'l';
                    itrCount++;
                    totalScore -= 50;
                    if(doesExceed(x,y,direction)) {
                        System.out.println("Exceed boundary, -2000 damage applied");
                        totalScore -= 2000;
                        totalScore += reward();
                        if(isGameOver(x, y, totalScore, itrCount)) {
                            evaluation(totalScore);
                            userActive = false;
                        }
                    }
                    else {
                        x--;
                        totalScore += reward();
                        if(isGameOver(x, y, totalScore, itrCount)) {
                            evaluation(totalScore);
                            userActive = false;
                        }
                    }
                    break;
                }
            System.out.println();
            }
    }
    
    /**
     * Prints a message for the user at the start of each iteration
     * @param x the x-coordinate of the robot
     * @param y the y-coordinate of the robot
     * @param itrCount the number of iterations made so far
     * @param totalScore the total score
     */
    public static void displayInfo(int x, int y, int itrCount, int totalScore) {
        System.out.printf("%s%d, %s%d%s%d%s%d\n", "For point (X=", x, "Y=",y, ") at iterations: ",
                itrCount, " the total score is: ", totalScore);
    }
    
    /**
     * Checks if the robot has exceeded the limits of the grid
     * @param x the x-coordinate of the robot
     * @param y the y-coordinate of the robot
     * @param direction the direction that the user inputs (either r, l, d or u)
     * @return true if the robot exceeds the limits of the grid or false if it doesn't
     */
    public static boolean doesExceed(int x, int y, char direction) { 
        if (x >= 4 && direction == ('r') || y >= 4 && direction == ('u'))
            return true;
        else 
            return x <= 0 && direction == ('l') || y <= 0 && direction == ('d');
    }
    
    /**
     * Gives the player a reward (points) depending on the number given when the dice is rolled
     * @return a certain number of points to the player
     */
    public static int reward() {
        Random rand = new Random();
        int dice = rand.nextInt(6) + 1;
        
        switch (dice) {
            case 1:
                System.out.println("Dice: 1, reward: -100");
                return -100;
            case 2:
                System.out.println("Dice: 2, reward: -200");
                return -200;
            case 3:
                System.out.println("Dice: 3, reward: -300");
                return -300;
            case 4:
                System.out.println("Dice: 4, reward: 300");
                return 300;
            case 5:
                System.out.println("Dice: 5, reward: 400");
                return 400;
            default:
                System.out.println("Dice: 6, reward: 600");
                return 600;
        }
    }
    
    /**
     * Removes or keeps the player's previous negative reward by flipping a coin that 
     * returns either heads or tails (for heads, the negative reward is applied;
     * for tails, the negative reward is removed) 
     * @param direction the direction input by the player
     * @param reward the initial reward
     * @return 0 or the initial reward depending if the user flipped head or tail
     */
    public static int punishOrMercy(char direction, int reward) {
        if (reward < 0 && direction == 'u') {
            if (Math.random() == 0) {
                System.out.println("Coin: tail | Mercy , the negative reward was removed");
                return 0;
            }
            else {
                System.out.println("Coin: head | No mercy, the negative rewarded is applied");
                return reward;
            }
        }
        else 
            return reward;
    }
    
    /**
     * It converts the case of an input name (the player will input their name) into title case
     * @param str the string input by the user
     * @return the string in title case
     */
    public static String toTileCase(String str) { 
        int idx = str.indexOf(' ');
        String fname = str.substring(0, idx);
        String lname = str.substring(idx + 1, str.length());
        
        fname = Character.toTitleCase(fname.charAt(0)) + fname.substring (1, idx);
        lname = Character.toTitleCase(lname.charAt(0)) + lname.substring(1, idx + 1);
        
        return fname + ' ' + lname;
    }
    
    /**
     * Prints a message based on the total score
     * @param totalScore the total score of the player by the end of the game
     */
    public static void evaluation(int totalScore) { 
        Scanner console = new Scanner (System.in);
        System.out.print("Please enter your name (only two words): ");
        String name = console.nextLine();
        String titleCaseName = toTileCase(name);
        
        if (totalScore >= 2000)
            System.out.printf("%s%s%s%d\n", "Victory, ", titleCaseName, 
                    ", your score is ", totalScore);
        else 
            System.out.printf("%s%s%s%d\n", "Mission failed, ", titleCaseName, 
                    ", your score is ", totalScore);
    }
    
    /**
     * Asks the user to input a valid direction
     * @return the direction (either 'l, 'r', 'u', 'd')
     */
    public static char inputDirection() {
        Scanner console = new Scanner (System.in);
        char direction;
        
        do { 
            System.out.print("Please enter the direction you want to move towards: ");
            direction = console.next().charAt(0);
        } while (!isDirectionValid(direction));
                
        return direction;
    }
    
    /**
     * Checks if the input direction is valid, only supports 'l', 'r', 'u', 'd'
     * @param direction the input direction
     * @return true if the direction is valid and false if it's not
     */
    public static boolean isDirectionValid(char direction) {
        return direction == 'u' || direction == 'd' || direction == 'l' || direction == 'r';
    }
    
    /**
     * Checks if the game is over according to the win or lose conditions
     * @param x the x-coordinate of the robot
     * @param y the y-coordinate of the robot
     * @param totalScore the total score 
     * @param itrCount the number of iterations made so far
     * @return true if one of the termination criterion was activated, and false otherwise
     */
    public static boolean isGameOver(int x, int y, int totalScore, int itrCount) {
        return (itrCount > 20 || totalScore < -1000 || totalScore > 2000 
                || x == 4 && y == 4 || x == 4 && y == 0);
    }
}
