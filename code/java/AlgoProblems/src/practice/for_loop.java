package practice;

public class for_loop {
    public static void main(String[] args) {
        int i=10;
        double a = 2.3322;
        boolean b = true ;
        // () , {}

        int h = 23;

        if( i < h ){
            System.out.println("Inside if statement");
        } else {
            System.out.println("False");
        }

        System.out.println( i != 2  );


        if(i==1){
            System.out.println("i is 1");
        } else if(i==3){
            System.out.println("i is 3");
        } else if(i== 10){
            System.out.println("i is 10");
        } else {
            System.out.println("None of the above");
        }


        String adi = "Sanyam is here";

        if(i == 10){
            if(adi.equals("Sanyam")){
                System.out.println("He is here");
            } else {
                System.out.println("Not here");
            }
        }  else {
            System.out.println("None of the above");
        }

        print(); 

    }

    public static void print(){
        System.out.println("Printing text");
    }

}
