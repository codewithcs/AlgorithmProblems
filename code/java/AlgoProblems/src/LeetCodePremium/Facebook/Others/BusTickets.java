package LeetCodePremium.Facebook.Others;

// AllInCall Assessment: Best Bus Tickets.
public class BusTickets {
    static int count = 0;

    public static void main(String[] args) {
        int[] d = {1, 2, 4};
        int N = 6;
        for(int i=0; i<d.length; i++){
            generateNumber(d, String.valueOf(d[i]), 1, N);
        }
        System.out.println("count is : " + count);
    }

    static void generateNumber(int[] d, String current, int length, int N){
        if(length >= N){
            if(checkBestTicket(current)){
                System.out.println("Ticket number is : " + current);
                count ++;
            }
            return;
        }

        for(int i=0; i<d.length; i++){
            generateNumber(d, current + d[i], length+1, N);
        }
    }

    static boolean checkBestTicket(String number){
        int left = 0;
        int right = number.length()-1;
        int leftSum =0 ; int rightSum =0;
        while(left<right){
            leftSum = leftSum + (int)number.charAt(left);
            rightSum = rightSum + (int)number.charAt(right);
            left++; right--;
        }

        return leftSum == rightSum;
    }
}
