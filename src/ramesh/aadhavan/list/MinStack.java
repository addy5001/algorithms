package ramesh.aadhavan.list;

public class MinStack {

    static class Entry {
        int value;
        int min;
        Entry next;

        public Entry(int value, int min) {
            this.value = value;
            this.min = min;
            next = null;
        }
    }

    Entry top;

    public void push(Integer value) {
        if(top == null) {
            top = new Entry(value, value);
        }
        else {
            Entry node;
            if(value > top.min) {
                node = new Entry(value, top.min);
            }
            else {
                node = new Entry(value, value);
            }

            node.next = top;
            top = node;
        }
    }

    public Integer peek() {
        return (top == null) ? null : top.value;
    }

    public Integer pop() {
        if(top == null)
            return null;

        Integer tmp = top.value;
        top = top.next;
        return tmp;
    }

    public Integer getMin() {
        return (top == null) ? null : top.min;
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();

        stack.push(5);
        stack.push(2);
        stack.push(100);
        stack.push(12);
        stack.push(-1);
        stack.push(-100);

        System.out.println(stack.peek());
        System.out.println(stack.getMin());
        System.out.println(stack.pop());
        System.out.println(stack.getMin());
        System.out.println(stack.pop());
        System.out.println(stack.getMin());

        stack.push(-120);

        System.out.println(stack.getMin());
    }
}
