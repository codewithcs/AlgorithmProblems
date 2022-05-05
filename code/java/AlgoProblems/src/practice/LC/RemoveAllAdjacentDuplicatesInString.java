package practice.LC;

public class RemoveAllAdjacentDuplicatesInString {
    public static void main(String[] args) {

    }

    // Time Limit Exceeded
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder(S);

        while(true){
            int a= 0;
            for(int i=1; i< sb.length(); i++){
                if(sb.charAt(i) == sb.charAt(i-1)){
                    sb.deleteCharAt(i);
                    sb.deleteCharAt(i-1);
                    a = 1;
                    break;
                }
            }

            if(a == 0) break;
        }


        return sb.toString();
    }
}
