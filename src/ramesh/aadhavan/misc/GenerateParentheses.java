package ramesh.aadhavan.misc;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public List<String> generate(int n) {
        List<String> parentheses = new ArrayList<>();
        _generate(n, 0, 0, 0, 0,new StringBuilder(), parentheses);
        return parentheses;
    }

    private void _generate(int n, int open, int closed, int valid,
                           int idx, StringBuilder sb, List<String> parentheses) {
        if(open > n || closed > open)
            return;

        if(valid == n && open == closed) {
            parentheses.add(sb.toString());
            return;
        }

        sb.insert(idx, '(');
        _generate(n, open+1, closed, valid, idx+1, sb, parentheses);
        sb.deleteCharAt(idx);

        sb.insert(idx, ')');
        _generate(n, open, closed+1, valid+1, idx+1, sb, parentheses);
        sb.deleteCharAt(idx);
    }

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> strings = generateParentheses.generate(3);
        for(String string : strings) {
            System.out.println(string);
        }
    }
}
