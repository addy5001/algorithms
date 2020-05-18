package ramesh.aadhavan.list;

import java.util.Queue;

public class StackUsingQueues {

    Queue<Integer> queue1 = new java.util.LinkedList<>();
    Queue<Integer> queue2 = new java.util.LinkedList<>();
    int top = Integer.MIN_VALUE;
    int active = -1;

    /** Initialize your data structure here. */
    public StackUsingQueues() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        if(active == -1 || active == 0) {
            queue1.add(x);
            top = x;
            active = 0;
        }
        else {
            queue2.add(x);
            top = x;
            active = 1;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(active == 0) {
            while(!queue1.isEmpty()) {
                int element = queue1.poll();
                if(queue1.isEmpty()) {
                    if(queue2.isEmpty())
                        top = Integer.MIN_VALUE;
                    return element;
                }
                else {
                    queue2.add(element);
                    top = element;
                    active = 1;
                }
            }
        }
        else {
            while(!queue2.isEmpty()) {
                int element = queue2.poll();
                if(queue2.isEmpty()) {
                    if(queue1.isEmpty())
                        top = Integer.MIN_VALUE;
                    return element;
                }
                else {
                    queue1.add(element);
                    top = element;
                    active = 0;
                }
            }
        }

        return Integer.MIN_VALUE;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
