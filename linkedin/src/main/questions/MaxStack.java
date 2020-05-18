package questions;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxStack {

    class Node {
        int val;
        int max;
        Node next;

        public Node(int val, int max) {
            this.val = val;
            this.max = max;
        }
    }

    Node top;

    /** initialize your data structure here. */
    public MaxStack() {
        top = null;
    }

    public void push(int x) {
        if(top == null)
            top = new Node(x, x);
        else {
            int currentMax = top.max;
            Node node = null;
            if(x > top.max) {
                node = new Node(x, x);
            }
            else {
                node = new Node(x, currentMax);
            }

            node.next = top;
            top = node;
        }
    }

    public int pop() {
        int val = top.val;
        top = top.next;
        return val;
    }

    public int top() {
        return top.val;
    }

    public int peekMax() {
        return top.max;
    }

    public int popMax() {
        int currentMax = top.max;
        Deque<Node> stack = new ArrayDeque<>();
        Node temp = top;
        Node prev = null;

        while(temp.val != currentMax) {
            stack.push(temp);
            prev = temp;
            temp = temp.next;
        }

        if(temp == top) {
            top = top.next;
        }
        else {
            prev.next = temp.next;
            int newMax;
            if(prev.next == null) {
                newMax = prev.val;
                prev.max = prev.val;
            }
            else {
                newMax = prev.next.max;
            }

            while (!stack.isEmpty()) {
                Node curr = stack.pop();
                if(curr.val > newMax)
                    newMax = curr.max;
                else
                    curr.max = newMax;
            }
        }

        return currentMax;
    }
}
