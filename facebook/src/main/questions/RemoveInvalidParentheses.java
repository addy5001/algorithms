package questions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses {

    int minRemoved = Integer.MAX_VALUE;
    Set<String> validExpressions = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        reset();
        _recurse(s, 0, new StringBuilder(), 0, 0, 0, 0);
        return new ArrayList<>(validExpressions);
    }

    private void reset() {
        minRemoved = Integer.MAX_VALUE;
        validExpressions.clear();
    }

    private void _recurse(String s, int idx,
                          StringBuilder sb, int sbIdx,
                          int leftCount, int rightCount, int removedCount) {
        if(idx == s.length()) {
            if(leftCount == rightCount) {
                if(removedCount <= minRemoved) {
                    String potentialResult = sb.toString();

                    if(removedCount < minRemoved) {
                        minRemoved = removedCount;
                        validExpressions.clear();
                    }

                    validExpressions.add(potentialResult);
                }
            }
        }
        else {
            if(s.charAt(idx) != '(' && s.charAt(idx) != ')') {
                sb.append(s.charAt(idx));
                _recurse(s, idx + 1, sb, sbIdx+1, leftCount, rightCount, removedCount);
                sb.deleteCharAt(sbIdx);
            }
            else {

                // Skip current parenthesis and proceed
                _recurse(s, idx+1, sb, sbIdx, leftCount, rightCount, removedCount+1);

                if(s.charAt(idx) == '(') {
                    sb.append('(');
                    _recurse(s, idx+1, sb, sbIdx+1, leftCount+1, rightCount, removedCount);
                    sb.deleteCharAt(sbIdx);
                }

                if(s.charAt(idx) == ')') {
                    if(rightCount < leftCount) {
                        sb.append(')');
                        _recurse(s, idx + 1, sb, sbIdx+1, leftCount, rightCount+1, removedCount);
                        sb.deleteCharAt(sbIdx);
                    }
                }
            }
        }
    }
}
