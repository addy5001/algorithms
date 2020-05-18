package questions;

class Reader4 {
    int read4(char[] buf) { return 0; }
}

public class ReadN extends Reader4 {
    public int read(char[] buf, int n) {
        int ptr = 0;
        int totalRead = 0;
        int count = 0;
        char[] temp = new char[4];

        while(totalRead < n) {
            if(ptr == 0)
                count = read4(temp);

            if(count == 0)
                break;

            while(totalRead < n && ptr < count) {
                buf[totalRead] = temp[ptr];
                totalRead++;
                ptr++;
            }

            if(ptr >= count)
                ptr = 0;
        }

        return totalRead;
    }
}
