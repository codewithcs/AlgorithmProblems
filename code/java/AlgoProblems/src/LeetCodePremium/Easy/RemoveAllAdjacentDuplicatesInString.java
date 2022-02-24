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

    // 2 Pointer approach.
    public String removeDuplicates2(String S) {
        int i = 0, j = 0;
        char[] res = S.toCharArray();
        for (; j < res.length; j++, i++) {
            res[i] = res[j];
            if (i > 0 && res[i-1] == res[j]){
                i -= 2;
            }
        }
        return new String(res, 0, i);
    }

    // Stack solution, O(n) time where n is a string length and O(n-d) space.
    // d is the total length for all duplicates.
    public String removeDuplicates3(String S) {
        StringBuilder sb = new StringBuilder();
        int sbLength = 0;

        for(char character : S.toCharArray()) {
            if (sbLength != 0 && character == sb.charAt(sbLength - 1)) { // check whether stack is empty or not.
                sb.deleteCharAt(sbLength-- - 1);
            } else {
                sb.append(character);
                sbLength++;
            }
        }

        return sb.toString();
    }

    public String removeDuplicates4(String S) {
        StringBuilder sb = new StringBuilder();
        sb.append(S.charAt(0));

        for(int i=1; i< S.length(); i++){
            if(sb.length() > 0  && S.charAt(i) == sb.charAt(sb.length()-1)){
                sb.deleteCharAt(sb.length()-1);
            } else {
                sb.append(S.charAt(i));
            }
        }

        return sb.toString();
    }
}
