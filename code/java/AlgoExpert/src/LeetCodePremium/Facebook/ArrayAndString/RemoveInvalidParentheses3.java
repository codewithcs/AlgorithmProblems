package LeetCodePremium.Facebook.ArrayAndString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses3 {
    int minRemoved = Integer.MAX_VALUE;

    public List<String> removeInvalidParentheses(String s) {
        Set<String> answer = new HashSet<>();
        traverse(s, answer, 0, 0, 0, 0, new StringBuilder() );
        return new ArrayList(answer);
    }

    public void traverse(String s, Set<String> answer, int currentIndex, int leftCount, int rightCount, int removedCharacters, StringBuilder currentString){
        if(currentIndex == s.length()){
            if(leftCount == rightCount ){
                if(removedCharacters <= minRemoved){
                    if(removedCharacters < minRemoved){
                        minRemoved = removedCharacters;
                    }
                    answer.add(currentString.toString());
                }
            }
        } else {
            char currentCharacter = s.charAt(currentIndex);
            int length = currentString.length();

            if(currentCharacter != '(' && currentCharacter != ')' ){
                currentString.append(currentCharacter);
                traverse(s, answer, currentIndex+1, leftCount, rightCount, removedCharacters, currentString);
                currentString.deleteCharAt(length);
            } else {
                if(currentCharacter == '('){
                    currentString.append('(');
                    traverse(s, answer, currentIndex+1, leftCount+1, rightCount, removedCharacters, currentString);
                    currentString.deleteCharAt(length);
                } else if(currentCharacter == ')'){
                    if(leftCount> rightCount){
                        currentString.append(')');
                        traverse(s, answer, currentIndex+1, leftCount, rightCount+1, removedCharacters, currentString);
                        currentString.deleteCharAt(length);
                    }
                }

                // Remove this parentheses (Actually just skip it). this part is will be common in both, so we can put it here.
                traverse(s, answer, currentIndex+1, leftCount, rightCount, removedCharacters+1, currentString);
            }
        }
    }
}
