package questions;

import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        return _dynamic(s, wordDict);
    }

    private boolean _wordBreak(String s, String output,
                               List<String> wordDict, int begin, int end) {
        if(begin == end) {
            System.out.println(output);
            return true;
        }

        for(int i=1; begin+i<=end; i++) {
            String substring = s.substring(begin, begin+i);
            if(wordDict.contains(substring)
                    && _wordBreak(s, String.join(" ", output, substring), wordDict,begin+i, end))
                return true;
        }

        return false;
    }

    private boolean _dynamic(String s, List<String> wordDict) {
        boolean[] grid = new boolean[s.length()+1];
        grid[0] = true;

        for(int i=1; i<=s.length(); i++) {
            for(int j=0; j<i; j++) {
                if(grid[j] && wordDict.contains(s.substring(j, i))) {
                    grid[i] = true;
                    break;
                }
            }
        }

        return grid[s.length()];
    }
}
