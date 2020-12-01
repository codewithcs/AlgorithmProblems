package LeetCodePremium.Amazon.ArrayAndStrings;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s.
You can return them in any order.

A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros.
For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245",
"192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> answer = new ArrayList<>();
        traverse(0, answer, 0, s, s);
        return answer;
    }

    // Output Limit Exceeded.
    public void traverse(int index, List<String> answer, int count, String current, String s){
        // Base Case
        if(count == 3){
            String[] values = current.split("\\.");
            for(String value: values){
                if(value.length()==0 || (value.length() > 1 && value.charAt(0) == '0' )) return;

                int intValue = 0;
                try{
                    intValue = Integer.parseInt(value);
                } catch(Exception e){
                    return;
                }

                if(intValue < 0 || intValue > 255){
                    return;
                }
            }

            answer.add(current);
            return;
        }

        for(int i=index; i<current.length()-1; i++){
            String currentString = current.substring(0, i+1) + "." + current.substring(i+1);
            traverse(i+2, answer, count+1, currentString, s);
        }
    }
}
