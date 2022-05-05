package practice.Arista_;

class banner3 {
    public static void main(String[] args) {
        System.out.println(print("123 4567890"));
    }
    static String CHAR = " 0123456789";

    public static String print(String pattern){
        StringBuilder sb = new StringBuilder();

        for(int row = 0; row < 8; row ++ ){
            sb.append('\n');
            for(char current: pattern.toCharArray()){
                int enc = CHAR.indexOf(current);
                if (enc < 0) continue;
                for(int column = 0; column < 8 ; column++){
                    if( (map[enc][row] & (1 << column) ) != 0){
                        sb.append('#');
                    } else {
                        sb.append(' ');
                    }
                }
            }
        }

        return sb.append('\n').toString();
    }

    static int A = 1;
    static int B = 1<<1; // 2
    static int C = 1<<2; // 4
    static int D = 1<<3; // 8
    static int E = 1<<4; // 16
    static int F = 1<<5; // 32
    static int G = 1<<6; // 64
    static int H = 1<<7; // 128

    static int[][] map = new int[][]{
            /* */       { 0, 0, 0, 0, 0, 0, 0, 0 },
            /*0*/      { B|C|D|E|F, A|F|G, A|E|G, A|D|G, A|C|G, A|B|G, B|C|D|E|F, 0},
            /*1*/      { G, F|G, G, G, G, G, G, 0},
            /*2*/      { B|C|D|E|F, A|G, G, C|D|E|F, B, A, A|B|C|D|E|F|G ,0},
            /*3*/      { B|C|D|E|F, A|G, G, C|D|E|F, G, A|G, B|C|D|E|F ,0},
            /*4*/      { A|F, A|F, A|F, B|C|D|E|F|G, F, F, F ,0},
            /*5*/      { A|B|C|D|E|F|G, A, A, B|C|D|E|F, G, A|G, B|C|D|E|F ,0},
            /*6*/      { B|C|D|E|F, A, A, A|B|C|D|E|F, A|G, A|G, B|C|D|E|F, 0},
            /*7*/      { B|C|D|E|F|G, G, F, E, D, C, B ,0},
            /*8*/      { B|C|D|E|F, A|G, A|G, B|C|D|E|F, A|G, A|G, B|C|D|E|F ,0},
            /*9*/      { B|C|D|E|F, A|G, A|G, B|C|D|E|F, G, G, B|C|D|E|F, 0},



    };

}
