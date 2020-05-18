package questions;

class VersionControl {
    boolean isBadVersion(int version) { return true; }
}

public class FirstBadVersion extends VersionControl {
    public int firstBadVersion(int n) {
        return _firstBadVersion(1, n);
    }

    private int _firstBadVersion(int low, int high) {
        int mid = low + (high-low)/2;

        if(isBadVersion(mid)) {
            if(mid==0 || !isBadVersion(mid-1))
                return mid;
            else
                return _firstBadVersion(low, mid-1);
        }
        else {
            return _firstBadVersion(mid+1, high);
        }

//        if((mid-1)>=0 &&
//                (isBadVersion(mid-1) == false && isBadVersion(mid) == true)) {
//            return mid;
//        }
//        else if(isBadVersion(mid)) {
//            return _firstBadVersion(low, mid-1);
//        }
//        else {
//            return _firstBadVersion(mid+1, high);
//        }
    }
}
