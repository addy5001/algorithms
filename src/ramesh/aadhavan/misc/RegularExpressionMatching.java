package ramesh.aadhavan.misc;

import java.util.Arrays;
import java.util.regex.Pattern;

public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        Boolean[][] grid = new Boolean[s.length()+1][p.length()+1];
        for (Boolean[] booleans : grid) {
            Arrays.fill(booleans, null);
        }
        return _dynamic(grid, 0, 0, s, p);
    }

    private boolean _isMatchRegex(String s, String p) {
        Pattern pattern = Pattern.compile(p);
        return pattern.matcher(s).matches();
    }

    private boolean _recurse(String s, String p) {
        if(p.isEmpty())
            return s.isEmpty();

        boolean firstMatch = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        if(p.length() >= 2 && p.charAt(1) == '*') {
            return (_recurse(s, p.substring(2)) || (firstMatch && _recurse(s.substring(1), p)));
        }
        else {
            return firstMatch && _recurse(s.substring(1), p.substring(1));
        }
    }

    private boolean _dynamic(Boolean[][] grid, int i, int j, String s, String p) {
        if(grid[i][j] != null) {
            return grid[i][j];
        }

        boolean result;
        if(j == p.length()) {
            result = i == s.length();
        }
        else {
            boolean firstMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                result = _dynamic(grid, i, j+2, s, p) || (firstMatch && _dynamic(grid, i+1, j, s, p));
            }
            else {
                result = firstMatch && _dynamic(grid, i+1, j+1, s, p);
            }
        }

        grid[i][j] = result;
        return result;
    }
}
