package practice.Arista_;

public class banner2 {
    public static void main(String[] args) {
       /* System.out.println("Output : " + (1<< 1));
        System.out.println("Output 2 : " + (1<< 2));
        System.out.println("Output : " + (1<< 3));
        System.out.println("Output 2 : " + (1<< 4));
        System.out.println("Output : " + (1<< 5));
        System.out.println("Output 2 : " + (1<< 6));
        System.out.println("Output : " + (1<< 7));*/
        System.out.println(print("1234567", '#'));
    }

    public static String print(String s, char stdCh) {
        StringBuilder sb = new StringBuilder();

        // 8 Rows. 8 Columns for each character.
        for (int row = 0; row < 8; row++) {
            sb.append('\n');
            for (char ch : s.toCharArray()) {
                int enc = CHAR_LIST.indexOf(ch);
                if (enc < 0) continue;
                for (int col = 0; col < 8; col++) {
                    sb.append(charToPrint(ch, stdCh));
                }
            }
        }
        return sb.append('\n').toString();
    }

    private static char charToPrint(char ch, char stdCh) {
        return stdCh;//return ch != ' ' && stdCh != 0 ? stdCh : ch;
    }

    private static boolean isPresent(int row, int enc, int col) {
            return false; //    return (map[enc][row] & 1 << col) != 0;
    }

    // @formatter:off
    private static final String CHAR_LIST = " 0123456789";//abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.,:!/\\|+-*=";

}
