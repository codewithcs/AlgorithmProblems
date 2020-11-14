package LeetCodePremium.Google.TreesAndGraphs;

/*
Given a robot cleaner in a room modeled as a grid.
Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
Design an algorithm to clean the entire room using only the 4 given APIs shown below.

Notes:
The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
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

    public void cleanRoom(Robot robot) {

    }
}
