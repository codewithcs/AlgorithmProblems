package LeetCode.Amazon.Recursion;

import java.util.ArrayList;
import java.util.List;

public class RestoreIP3 {
    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<>();
        helper(res,new StringBuilder(),s,0,0);
        return res;
    }
    //start: starting index, count: numbers added into stringbuilder
    public void helper(List<String> res, StringBuilder temp, String s,int start, int count){
        // if we are at the end and added 4 elements, add to result
        if (start==s.length() && count==4)
            res.add(temp.toString());
            // if we are not at the end and already has 4 elements-> invalid
        else if (count==4) return;

        for(int i=start; i<s.length(); i++){
            String curr = s.substring(start,i+1);
            int value = Integer.parseInt(curr);
            // check whether the number is like "0010" or "00";
            if (curr.length()>1 && curr.charAt(0)=='0') return;
            // out of range
            if (value>255) return;
            // less or equal to 255
            if (value<=255 && value>=0){
                // Backtracking
                StringBuilder rollback = new StringBuilder(temp);
                temp.append(s.substring(start,i+1));
                if (count<3) temp.append(".");

                helper(res,temp,s,i+1,count+1);
                // Rollback to previous state
                temp = rollback;
            }
        }
    }

    // Best Solution:
    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<String>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses2(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public void dfs(String s, int segId, int segStart) {
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        if (segStart == s.length()) {
            return;
        }

        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }
}
