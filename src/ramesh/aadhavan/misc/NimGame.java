package ramesh.aadhavan.misc;

public class NimGame {

    public boolean canWinNim(int n) {
        if(n < 4)
            return false;
        else if(n == 4)
            return true;
        else {
            return canWinNim(n-1) || canWinNim(n-2) || canWinNim(n-3);
        }
    }

}
