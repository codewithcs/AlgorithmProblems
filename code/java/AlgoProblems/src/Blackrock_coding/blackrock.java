package Blackrock_coding;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class blackrock {

    static  final Map<Character, String[]> CHARS = new HashMap<>();
    static {
        CHARS.put(':', new String[] {"     ", "  #  ", "     ", "  #  ", "     "});
        CHARS.put('0', new String[] {"#####", "#   #", "#   #", "#   #", "#####"});
        CHARS.put('1', new String[] {"    #", "    #", "    #", "    #", "    #"});
        CHARS.put('2', new String[] {"#####", "    #", "#####", "#    ", "#####"});
        CHARS.put('3', new String[] {"#####", "    #", "#####", "    #", "#####"});
        CHARS.put('4', new String[] {"#   #", "#   #", "#####", "    #", "    #"});
        CHARS.put('5', new String[] {"#####", "#    ", "#####", "    #", "#####"});
        CHARS.put('6', new String[] {"#####", "#    ", "#####", "#   #", "#####"});
        CHARS.put('7', new String[] {"#####", "    #", "    #", "    #", "    #"});
        CHARS.put('8', new String[] {"#####", "#   #", "#####", "#   #", "#####"});
        CHARS.put('9', new String[] {"#####", "#   #", "#####", "    #", "    #"});
    }

    static class Pattern{
        String time;
        String[][] strs;
        String[] pattern;
        int height ;

        public Pattern(String s){
            setHeight();
            time = s;
            strs = initializeStrs(time);
            pattern = initializePattern();
        }

        String[][] initializeStrs(String s){
            return s.chars()
                    .mapToObj(c -> CHARS.get((char)c))
                    .toArray(String[][]::new);
        }

        String[] initializePattern(){
            return IntStream.range(0, height)
                    .mapToObj(i -> IntStream.range(0, getLength())
                            .mapToObj(j -> strs[j][i])
                            .collect(Collectors.joining(" ")))
                    .toArray(String[]::new);
        }

        void setHeight(){
            height = CHARS.values().iterator().next().length;
        }

        int getLength(){
            return time.length();
        }

        int getHeight(){
            return height;
        }

        void printPattern(){
            Stream.of(pattern).forEach(System.out::println);
        }

    }

    public static void main(String[] args) {
        Pattern p = new Pattern("90:11");
        p.printPattern();
    }

}


