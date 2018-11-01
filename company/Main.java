package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static Logger LOGGER = Logger.getLogger(Robot.class.getName());

    public static void main(String[] args) {
        System.out.println("Enter the input (*Empty line to end input)");
        Scanner keyboard = new Scanner(System.in);
        String line;
        Robot robot = null;
        int i = 0, maxX = 0, maxY = 0;
        Queue<String[]> positions = new LinkedList<>();
        Queue<char []> actions = new LinkedList<>();
        // Read from console and store the set of positions and actions in a queue
        while (keyboard.hasNextLine()) {
            try {
                line = keyboard.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                if (i == 0) {
                    int [] inputArray = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                    if (inputArray.length != 2){
                        throw new RuntimeException("Invalid input for upper-right coordinates");
                    }
                    maxX = inputArray [0];
                    maxY = inputArray [1];
                } else if ((i & 1) == 1) {
                    String [] inputArray = line.split(" ");
                    if (inputArray.length != 3){
                        throw new RuntimeException("Invalid set of inputs for the initial position");
                    }
                    ((LinkedList<String[]>) positions).add(inputArray);
                } else {
                    ((LinkedList<char []>) actions).add(line.toCharArray());
                }
                i++;
            }
            catch (Exception e) {
                LOGGER.severe("Exiting! Error while reading input: "+e);
                return;
            }
        }

        if ((i & 1) == 0) {
            LOGGER.severe("Exiting! Mismatched inputs");
            return;
        }

        // Process each input pairs
        while (positions.peek() != null){
            String [] bufferArray = positions.remove();
            try {
                if (robot == null) {
                    robot = new Robot(Integer.parseInt(bufferArray[0]), Integer.parseInt(bufferArray[1]), bufferArray[2].charAt(0),maxX,maxY );
                } else {
                    robot.restart(Integer.parseInt(bufferArray[0]), Integer.parseInt(bufferArray[1]), bufferArray[2].charAt(0));
                }
                char[] actionSequence = actions.remove();
                for (i = 0; i < actionSequence.length; i++) {
                    robot.communicate(actionSequence[i]);
                }
                robot.dispalyValues();
            } catch (Exception e) {
                LOGGER.severe("Exiting! Error in inputs: "+e);
            }
        }
    }
}
