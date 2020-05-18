package ramesh.aadhavan.misc;

import java.util.Deque;
import java.util.LinkedList;

public class MinimumToRemoveParentheses {
    public String minRemoveToMakeValid(String s) {
        return _minRemoveToMakeValid(s);
    }

    private class AnnotatedNode {
        Character parenthesis;
        int idx;

        public AnnotatedNode(Character parenthesis, int idx) {
            this.parenthesis = parenthesis;
            this.idx = idx;
        }
    }

    private String _minRemoveToMakeValid(String s) {
        Deque<AnnotatedNode> deque = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(')
                deque.push(new AnnotatedNode('(', i));
            else if(s.charAt(i) == ')') {
                AnnotatedNode topNode = deque.peek();
                if(topNode == null) {
                    deque.push(new AnnotatedNode(')', i));
                }
                else {
                    if (topNode.parenthesis == '(') {
                        deque.pop();
                    } else {
                        deque.push(new AnnotatedNode(')', i));
                    }
                }
            }
        }

        int start = 0;
        while(!deque.isEmpty()) {
            AnnotatedNode node = deque.pollLast();
            sb.append(s, start, node.idx);
            start = node.idx+1;
        }

        if(start < s.length()) {
            sb.append(s, start, s.length());
        }

        return sb.toString();
    }
}
