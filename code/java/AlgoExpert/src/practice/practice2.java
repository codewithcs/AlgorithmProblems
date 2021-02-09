package practice;

public class practice2 {
    public static void main(String[] args) {
       // System.out.println(Integer.MAX_VALUE);
        boolean b = true;
        for(int i=0; i< 5; i++){
            if(b){
                i = 2;
                b = false;
            }
            System.out.println("i is  : " + i);
        }

    }
}
