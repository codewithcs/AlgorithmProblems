package LeetCodePremium.Easy;

/*
Given a string S of lowercase letters, a duplicate removal consists of
choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.
It is guaranteed the answer is unique.

Note:
1 <= S.length <= 20000
S consists only of English lowercase letters.
 */
public class RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder(S);
        boolean foundAtLeastOne = false;

        do{
            foundAtLeastOne = false;
            for(int i=0; i< sb.length()-1; i++){
                if(sb.charAt(i) == sb.charAt(i+1)){
                    sb = new StringBuilder(sb.substring(0, i) + sb.substring(i+2));
                    foundAtLeastOne = true;
                }
            }
        } while(foundAtLeastOne);

        return sb.toString();
    }
}
