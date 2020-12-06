package LeetCodePremium.Facebook.ArrayAndString;

/*
Given a file and assume that you can only read the file using a given method read4,
implement a method read to read n characters.
Your method read may be called multiple times.

Note:
Consider that you cannot manipulate the file directly, the file is only accessible for
read4 but not for read.
The read function may be called multiple times.
Please remember to RESET your class variables declared in Solution, as
static/class variables are persisted across multiple test cases. Please see here for more details.
You may assume the destination buffer array, buf, is guaranteed to have enough space
for storing n characters.
It is guaranteed that in a given test case the same buffer buf is called by read.
 */

class Reader{
    int read4(char[] buf4){
        return 1;
    }
}
public class ReadNCharactersGivenRead4_2_Call_Multiple_Times extends Reader{
    private char[] r4Buf = new char[4];
    private int r4BufLen = -1;
    private int curChar = 4;
    private boolean eof = false;

    public int read(char[] buf, int n) {
        int i = 0;
        for (; i < n; i++) {
            if (eof && curChar == r4BufLen) {
                break;
            }
            if (curChar == 4) {
                r4BufLen = read4(r4Buf);
                curChar = 0;
                if (r4BufLen < 4)
                    eof = true;
                if (r4BufLen == 0) // an edge case
                    return i;
            }
            if (curChar < r4BufLen) {
                buf[i] = r4Buf[curChar];
                curChar++;
            }
        }
        return i;
    }

    // Solution 2:
    char[] tmp = new char[4];
    int tIdx = 0;
    int tSize = 0;
    public int read2(char[] buf, int n) {
        int idx = 0;
        while (idx < n) {
            if (tIdx == tSize) {
                tSize = read4(tmp);
                tIdx = 0;
                if (tSize == 0) {
                    break;
                }
            }
            buf[idx++] = tmp[tIdx++];
        }
        return idx;
    }

    // Solution 3:
    char[] buffer = new char[4];
    int readIdx = 0;
    int readChars = 0;

    public int read3(char[] buf, int n) {
        int idx = 0;
        while (idx < n ) {
            if(readIdx == readChars){ // readIdx [0, 3], readChars [0, 4]
                readChars = read4(buffer); readIdx=0;
                if(readChars == 0){
                    break;
                }
            }
            buf[idx] = buffer[readIdx];
            idx++; readIdx++;
        }
        return idx;
    }
}