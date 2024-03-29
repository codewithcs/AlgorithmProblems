package LeetCode.Google.TreesAndGraphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given a robot cleaner in a room modeled as a grid.
Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
Design an algorithm to clean the entire room using only the 4 given APIs shown below.

Notes:
The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded".

In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.
 */
public class RobotRoomCleaner {

      // This is the robot's control interface.
      // You should not implement it, or speculate about its implementation
      interface Robot {
          // Returns true if the cell in front is open and robot moves into the cell.
          // Returns false if the cell in front is blocked and robot stays in the current cell.
          public boolean move();

          // Robot will stay in the same cell after calling turnLeft/turnRight.
          // Each turn will be 90 degrees.
          public void turnLeft();
          public void turnRight();

          // Clean the current cell.
          public void clean();
      }

    Map<String, Boolean> map = new HashMap<>();

    public void cleanRoom(Robot robot) {
        int currentX = 0;
        int currentY = 0;
        clean(robot, currentX, currentY);
    }

    public void clean(Robot robot, int x, int y){
        String s = x + "-" + y;

        if(map.containsKey(s)){
            return ;
        }

        map.put(s, true);

        robot.clean();

        boolean canMove = robot.move();
        if(canMove){ // Up
            clean(robot, x, y+1);
            originalPosition(robot);
        }

        robot.turnRight();

        canMove = robot.move();

        if(canMove){ // Right
            clean(robot, x+1, y);
            originalPosition(robot);
        }

        robot.turnRight(); // Down

        canMove = robot.move();

        if(canMove){
            clean(robot, x, y-1);
            originalPosition(robot);
        }

        canMove = robot.move(); // Left

        if(canMove){
            clean(robot, x-1, y);
            originalPosition(robot);
        }

    }

    public void originalPosition(Robot robot){
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    // Approach 2:
    public void cleanRoom2(Robot robot) {
        // A number can be added to each visited cell
        // use string to identify the class
        Set<String> set = new HashSet<>();
        int cur_dir = 0;   // 0: up, 90: right, 180: down, 270: left
        backtrack(robot, set, 0, 0, 0);
    }

    public void backtrack(Robot robot, Set<String> set, int i,
                          int j, int cur_dir) {
        String tmp = i + "->" + j;
        if(set.contains(tmp)) {
            return;
        }

        robot.clean();
        set.add(tmp);

        for(int n = 0; n < 4; n++) {
            // the robot can to four directions, we use right turn
            if(robot.move()) {
                // can go directly. Find the (x, y) for the next cell based on current direction
                int x = i, y = j;
                switch(cur_dir) {
                    case 0:
                        // go up, reduce i
                        x = i-1;
                        break;
                    case 90:
                        // go right
                        y = j+1;
                        break;
                    case 180:
                        // go down
                        x = i+1;
                        break;
                    case 270:
                        // go left
                        y = j-1;
                        break;
                    default:
                        break;
                }

                backtrack(robot, set, x, y, cur_dir);
                // go back to the starting position
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnRight();
                robot.turnRight();

            }
            // turn to next direction
            robot.turnRight();
            cur_dir += 90;
            cur_dir %= 360;
        }

    }
}
