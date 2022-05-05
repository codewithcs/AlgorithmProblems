package practice.Arista_;

public class banner_print_practice {

    public static void main(String[] args) {
        String s = "1234567890";

        StringBuilder sb = new StringBuilder();

        // We are printing the pattern row by row.
        // Think in terms of final pattern we want to print.
        for(int row=0; row< 8; row++){
            for(char current: s.toCharArray()) {
                for (int col = 0; col < 8; col++) {
                    int index = CHARS.indexOf(current);

                    // map[1][0] && 1<< 0,1, 2, 3, 4
                    // Check which bits are set for the character.
                    if( (map[index][row] & 1<< col) != 0) {
                        sb.append('#');
                    } else {
                        sb.append(' ');
                    }

                }
            }
            sb.append('\n');
        }

        System.out.println(sb.append('\n').toString());
    }

    static String CHARS = "0123456789";
    static int A = 1;
    static int B = 1<< 1;
    static int C = 1<< 2;
    static int D = 1 << 3;
    static int E = 1<< 4;
    static int F = 1<< 5;
    static int G = 1 << 6;
   // static int H = 1<<7;

    static int map[][] = new int[][]{
        /*0*/    {C|D|E, B|F, A|C|G, A|D|G, A|E|G, B|F, C|D|E, 0},
        /*1*/ {D, C|D, B|D, D, D, D, B|C|D|E|F, 0},
              {B|C|D|E|F, A|G, G, B|C|D|E|F, A, A, A|B|C|D|E|F|G, 0 },
              {B|C|D|E|F, A|G, G, B|C|D|E|F, G, A|G, B|C|D|E|F, 0},
         /*4*/   {A, A|E, A|E, A|B|C|D|E|F|G, F, F, F, 0 },
            {A|B|C|D|E|F|G, A, A, B|C|D|E|F, G, A|G, B|C|D|E|F, 0 },
            {B|C|D|E|F, A|G, A, A|B|C|D|E|F, A|G, A|G, B|C|D|E|F, 0},
            {A|B|C|D|E|F|G, A|F, E, D, C, C, B, 0},
            {B|C|D|E|F, A|G, A|G, B|C|D|E|F, A|G, A|G, B|C|D|E|F, 0},
            /*9*/   {B|C|D|E|F, A|G, A|G, B|C|D|E|F|G, G, A|G, B|C|D|E|F, 0}


    };




}
