package LeetCode.Medium;

/*
On an infinite plane, a robot initially stands at (0, 0) and faces north.
The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degrees to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

Constraints:
1 <= instructions.length <= 100, Important clarifying question to ask.
instructions[i] is 'G', 'L' or, 'R'.
 */
public class RobotBoundedInCircle {
    public static void main(String[] args) {
    }

    public boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int direction = 0; // 1, 2, 3

        for(int i=1; i<=4 ; i++){
            for(char current: instructions.toCharArray()){
                if(current == 'G'){
                    if(direction == 0){
                        y = y + 1;
                    } else if(direction == 1){
                        x = x - 1;
                    } else if(direction == 2){
                        y = y -1;
                    } else {
                        x = x + 1;
                    }
                } else if(current == 'L'){
                    direction = (direction+1)%4;
                } else {
                    direction = (direction+3)%4; // -1 % 4 gives -1 instead of + 3.
                }
            }
        }

        return x== 0 && y== 0 ;
    }

    public boolean isRobotBounded2(String instructions) {
        // north = 0, east = 1, south = 2, west = 3
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Initial position is in the center
        int x = 0, y = 0;
        // facing north
        int idx = 0;

        for (char i : instructions.toCharArray()) {
            if (i == 'L') {
                idx = (idx + 3) % 4; // We add +3 here because South is the 3rd value in the directions array.
            }
            else if (i == 'R') {
                idx = (idx + 1) % 4;
            }
            else {
                x += directions[idx][0];
                y += directions[idx][1];
            }
        }

        // after one cycle:
        // robot returns into initial position
        // or robot doesn't face north
        return (x == 0 && y == 0) || (idx != 0);
    }
}
