package LeetCodePremium.Facebook.ArrayAndString;

/*
Given a file and assume that you can only read the file using a given method read4,
implement a method to read n characters.

Method read4:
The API read4 reads 4 consecutive characters from the file, then writes
those characters into the buffer array buf4.

The return value is the number of actual characters read.
Note that read4() has its own file pointer, much like FILE *fp in C.

Method read:
By using the read4 method, implement the method read that reads n characters from the file
and store it in the buffer array buf. Consider that you cannot manipulate the file directly.
The return value is the number of actual characters read.

Note:
Consider that you cannot manipulate the file directly,
the file is only accessible for read4 but not for read.
The read function will only be called once for each test case.
You may assume the destination buffer array, buf,
is guaranteed to have enough space for storing n characters.
 */

class Reader4{
    int read4(char[] buf4){
        return 0;
    }
}
public class ReadNCharactersGivenRead4 extends Reader4{

    // O(n) time to copy N characters, O(1) space to keep 4 elements.
    public int read(char[] buf, int n) {
        int copiedChars = 0, readChars = 4;
        char[] buf4 = new char[4];

        while (copiedChars < n && readChars == 4) {
            readChars = read4(buf4);
            for (int i = 0; i < readChars; ++i) {
                if (copiedChars == n) { // "leetcode", n=5 We will have "code" we have to stop at 'c'.
                    return copiedChars;
                }
                buf[copiedChars] = buf4[i];
                ++copiedChars;
            }
        }
        return copiedChars;
    }
}
