package ramesh.aadhavan.heap;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryHeap {
    static class HNode {
        public int value;
        public HNode left;
        public HNode right;

        public HNode(int value) {
            this.value = value;
        }
    }

    public HNode tip;

    public BinaryHeap() {
    }

    public void add(int val) {
        if(tip == null) {
            tip = new HNode(val);
            return;
        }

        add(tip, val);
    }

    private void add(HNode node, int val) {
        if(node.value < val) {
            int min = node.value;
            node.value = val;
            add(node, min);
        }
        else {
            if(node.left == null)
                node.left = new HNode(val);
            else if(node.right == null)
                node.right = new HNode(val);
            else {
                if(val > node.left.value)
                    add(node.left, val);
                else if(val > node.right.value)
                    add(node.right, val);
                else {
                    int diff1 = node.left.value - val;
                    int diff2 = node.right.value - val;

                    if(diff1 < diff2)
                        add(node.left, val);
                    else
                        add(node.right, val);
                }
            }
        }
    }

    public int peek() {
        return (tip != null) ? tip.value : -1;
    }

    public void levelOrder() {
        System.out.println("------------LEVEL ORDER TRAVERSAL------------");
        levelOrder(this.tip);
    }

    private void levelOrder(HNode node) {
        if(node == null)
            return;

        Queue<HNode> queue = new ArrayDeque<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            HNode temp = queue.poll();
            System.out.print(temp.value + " ");

            if(temp.left != null)
                queue.offer(temp.left);

            if(temp.right != null)
                queue.offer(temp.right);
        }
    }

    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap();
        heap.add(10);
        heap.add(20);
        heap.add(5);
        heap.add(100);
        heap.add(200);
        heap.add(150);
        heap.add(1);

        System.out.println(heap.peek());
        heap.levelOrder();
    }
}
