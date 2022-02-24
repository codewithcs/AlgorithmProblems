package LeetCodePremium.Facebook.ArrayAndString;

public class AddBinary {
    public String addBinary(String a, String b) {
        int i=a.length()-1; int j=b.length()-1;
        StringBuilder result = new StringBuilder() ; int carry =0;

        while(i>=0 || j>=0){
            int first = i>=0 ? a.charAt(i) - '0' : 0;
            int second = j>=0 ? b.charAt(j) - '0': 0 ;

            int sum = first+second+carry == 2 || first+second+carry == 0? 0 : 1;
            carry = first+second+carry >=2 ? 1 : 0;

            result.append(sum);
            i--; j--;
        }
        if(carry==1){
            result = result.append("1");
        }
        return result.reverse().toString();
    }

    public String addBinary2(String a, String b) {
        int i=a.length()-1; int j=b.length()-1;
        StringBuilder result = new StringBuilder() ; int carry =0;

        while(i>=0 || j>=0){
            int first = i>=0 ? a.charAt(i) - '0' : 0;
            int second = j>=0 ? b.charAt(j) - '0': 0 ;

            int sum = first^second^carry;
            carry = first+second+carry >=2 ? 1 : 0;

            result.append(sum);
            i--; j--;
        }
        if(carry==1){
            result = result.append("1");
        }
        return result.reverse().toString();
    }

    public String addBinary3(String a, String b) {
        StringBuilder str = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i) - '0';
            if (j >= 0) sum += b.charAt(j) - '0';
            str.append(sum % 2);
            carry = sum / 2;
            i--;
            j--;
        }
        if (carry != 0) str.append(carry);
        return str.reverse().toString();
    }
}
