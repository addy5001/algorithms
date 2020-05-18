package ramesh.aadhavan.bits;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {

    /**
     * Use recursion
     * Left shift 1 by n-1
     * Add toAdd to all elements in reverse
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        if(n == 0) {
            List<Integer> result = new ArrayList<>();
            result.add(0);
            return result;
        }

        List<Integer> result = grayCode(n-1);
        int toAdd = 1 << (n-1);

        for(int i=result.size()-1; i>=0; i--) {
            result.add(toAdd + result.get(i));
        }

        return result;
    }
}
