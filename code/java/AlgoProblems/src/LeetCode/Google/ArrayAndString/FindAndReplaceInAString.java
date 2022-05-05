package LeetCode.Google.ArrayAndString;

/*
To some string S, we will perform some replacement operations that replace
groups of letters with new ones (not necessarily the same size).

Each replacement operation has 3 parameters: a starting index i, a source word x
and a target word y.  The rule is that if x starts at position i in the original
string S, then we will replace that occurrence of x with y.  If not, we do nothing.

For example, if we have S = "abcd" and we have some replacement operation i = 2,
x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S,
we will replace it with "ffff".

Using another example on S = "abcd", if we have both the replacement operation i = 0,
x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff",
this second operation does nothing because in the original string S[2] = 'c',
which doesn't match x[0] = 'e'.

All these operations occur simultaneously.  It's guaranteed that there won't be any overlap
in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.

Constraints:
0 <= S.length <= 1000
S consists of only lowercase English letters.
0 <= indexes.length <= 100
0 <= indexes[i] < S.length
sources.length == indexes.length
targets.length == indexes.length
1 <= sources[i].length, targets[i].length <= 50
sources[i] and targets[i] consist of only lowercase English letters.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindAndReplaceInAString {
    public static void main(String[] args) {
        String S  = "wqzzcbnwxc";
        int[] indexes = { 5, 2, 7 };
        String[] sources = { "bn", "zzc", "wxc" };
        String[] targets = { "t", "lwb", "nee" };
        System.out.println(findReplaceString(S, indexes, sources, targets));
    }

    public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        if(S.length() == 0) return  "";

        String result = "";

        String duplicate = new String(S);
        int currentIndex = indexes[0];

        for(int i=0; i<targets.length; i++){
            int index = currentIndex ;
            String source = sources[i];
            int sourceLength = source.length();

            String target = targets[i];
            int targetLength = target.length();
            System.out.println("duplicate is : " + duplicate);
            System.out.println("index is : " + index);
            System.out.println("sourceLength is : " + sourceLength);
            System.out.println("source is : " + source);

            if(!duplicate.substring(index, index + sourceLength).equals(source)){
                continue;
            }

            result = duplicate.substring(0, index) + targets[i] + duplicate.substring(index + sourceLength, duplicate.length());

            if(i<= targets.length-2){
                if( indexes[i+1] > indexes[i]){
                    currentIndex = targetLength-sourceLength + indexes[i+1];
                } else {
                    currentIndex = indexes[i+1];
                }

                for(int j=i+2; j<indexes.length; j++){
                    if(indexes[j] > indexes[i]){
                        indexes[j] = targetLength-sourceLength + indexes[j];
                    }
                }
            }

            duplicate = new String(result);
        }


        return result.length() == 0 ? S : result;
    }

    public String findReplaceString2(String S, int[] indexes, String[] sources, String[] targets) {
        int N = S.length();
        int[] match = new int[N];
        Arrays.fill(match, -1);

        for (int i = 0; i < indexes.length; ++i) {
            int ix = indexes[i];
            if (S.substring(ix, ix + sources[i].length()).equals(sources[i])) {
                match[ix] = i;
            }
        }

        StringBuilder ans = new StringBuilder();
        int ix = 0;
        while (ix < N) {
            if (match[ix] >= 0) {
                ans.append(targets[match[ix]]);
                ix += sources[match[ix]].length();
            } else {
                ans.append(S.charAt(ix++));
            }
        }
        return ans.toString();
    }

    public String findReplaceString3(String S, int[] indexes, String[] sources, String[] targets) {
        if(S.length() == 0) return  "";

        int[] match = new int[S.length()];
        Arrays.fill(match, -1);

        // Mark the indices where we want to do the replacement and the value of the match[i] represents the index of sources[] or targets[].
        for(int i=0 ; i<indexes.length; i++){
            if(S.substring(indexes[i], indexes[i] + sources[i].length()).equals(sources[i])){
                match[indexes[i]] = i;
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<S.length(); ){
            System.out.println("i is : " + i);
            System.out.println("match["+ i + "] is : " + match[i]);
            if(match[i] >=0 ){
                sb.append(targets[match[i]]);
                i = i + sources[match[i]].length();
            } else {
                sb.append(S.charAt(i)); i++;
            }
        }

        return sb.toString();
    }

    public String findReplaceString4(String S, int[] indexes, String[] sources, String[] targets) {
        Map<Integer, String> map = new HashMap<>();
        Map<Integer, Integer> length = new HashMap<>();
        for (int i = 0; i < indexes.length; i++) {
            if (S.substring(indexes[i], indexes[i] + sources[i].length()).equals(sources[i])) {
                map.put(indexes[i], targets[i]);
                length.put(indexes[i], sources[i].length());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (map.containsKey(i)) {
                sb.append(map.get(i));
                i += length.get(i) - 1;
            } else {
                sb.append(S.charAt(i));
            }
        }

        return sb.toString();
    }
}
