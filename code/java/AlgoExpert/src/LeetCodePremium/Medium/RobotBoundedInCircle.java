package LeetCodePremium.Medium;

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
}
