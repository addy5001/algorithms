package ramesh.aadhavan.tree;

import ramesh.aadhavan.helpers.Tuple;
import ramesh.aadhavan.list.DoublyLinkedList;
import ramesh.aadhavan.list.DoublyLinkedListNode;

import java.util.*;
import java.util.stream.Collectors;

public class BinarySearchTree {

    public static class Node implements Comparable<Node> {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        void setRight(Node right) {
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(value, o.value);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node) {
                Node comparable = (Node) obj;
                if(this.value == ((Node) obj).value)
                    return true;
            }
            return false;
        }
    }

    public Node rootNode;

    public BinarySearchTree() {
        rootNode = null;
    }

    void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public void addNode(int value) {
        if(Objects.isNull(this.rootNode)) {
            this.rootNode = new Node(value, null, null);
            return;
        }

        if(searchNode(value)) {
            System.out.println("Value already present");
            return;
        }

        addNode(this.rootNode, new Node(value, null, null));
    }

    private void addNode(Node parentNode, Node node) {
        if(parentNode.compareTo(node) > 0) {
            if(Objects.isNull(parentNode.left))
                parentNode.left = node;
            else
                addNode(parentNode.left, node);
        }
        else {
            if(Objects.isNull(parentNode.right))
                parentNode.right = node;
            else
                addNode(parentNode.right, node);
        }
    }

    public boolean searchNode(int value) {
        Node pointer = this.rootNode;
        while(Objects.nonNull(pointer)) {
            if(value == pointer.value)
                return true;
            else if(value < pointer.value)
                pointer = pointer.left;
            else
                pointer = pointer.right;
        }
        return false;
    }

    public void inorder() {
        System.out.println("------------INORDER TRAVERSAL------------");
        _inorderIterative(this.rootNode);
    }

    private void inorder(Node node) {
        if(Objects.nonNull(node)) {
            inorder(node.left);
            System.out.print(node.value+" ");
            inorder(node.right);
        }
    }

    private void _inorderIterative(Node node) {
        Deque<Node> stack = new ArrayDeque<>();
        Node temp = node;

        while(!stack.isEmpty() || temp != null) {
            while(temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            Node topNode = stack.pop();
            System.out.println(topNode.value);

            temp = topNode.right;
        }
    }

    public void preorder() {
        System.out.println("------------PREORDER TRAVERSAL------------");
        preorderIterative(this.rootNode);
    }

    private void preorder(Node node) {
        if(Objects.nonNull(node)) {
            System.out.print(node.value+" ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    private void preorderIterative(Node node) {
        if(node == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while(!stack.empty()) {
            Node temp = stack.pop();
            System.out.print(temp.value + " ");
            if(temp.right!=null)
                stack.push(temp.right);

            if(temp.left!=null)
                stack.push(temp.left);
        }
    }

    public void levelOrder() {
        System.out.println("------------LEVEL ORDER TRAVERSAL------------");
        levelOrder(this.rootNode);
    }

    private void levelOrder(Node node) {
        if(node == null)
            return;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.value + " ");

            if(temp.left != null)
                queue.offer(temp.left);

            if(temp.right != null)
                queue.offer(temp.right);
        }
    }

    public void spiralOrder() {
        System.out.println("------------SPIRAL ORDER TRAVERSAL------------");
        spiralOrder(this.rootNode);
    }

    private void spiralOrder(Node node) {
        if(node == null)
            return;

        boolean levelEven=true;
        Deque<Node> stackEven = new ArrayDeque<>();
        Deque<Node> stackOdd = new ArrayDeque<>();
        stackEven.push(node);

        while(!stackEven.isEmpty() || !stackOdd.isEmpty()) {
            if(levelEven) {
                Node temp = stackEven.pop();
                System.out.print(temp.value + " ");

                if(temp.left != null)
                    stackOdd.push(temp.left);
                if(temp.right != null)
                    stackOdd.push(temp.right);

                if(stackEven.isEmpty()) {
                    levelEven = false;
                    System.out.println();
                }
            }
            else {
                Node temp = stackOdd.pop();
                System.out.print(temp.value + " ");

                if(temp.right != null)
                    stackEven.push(temp.right);
                if(temp.left != null)
                    stackEven.push(temp.left);

                if(stackOdd.isEmpty()) {
                    levelEven = true;
                    System.out.println();
                }
            }
        }
    }

    public void postorder() {
        System.out.println("------------POSTORDER TRAVERSAL------------");
        _postorderIterative(this.rootNode);
    }

    private void postorder(Node node) {
        if(Objects.nonNull(node)) {
            preorder(node.left);
            preorder(node.right);
            System.out.print(node.value+" ");
        }
    }

    private void _postorderIterative(Node node) {
        Deque<Node> oldStack = new ArrayDeque<>();
        Deque<Node> newStack = new ArrayDeque<>();
        newStack.push(node);

        while(!newStack.isEmpty()) {
            Node parent = newStack.pop();
            if(parent.left != null)
                newStack.push(parent.left);

            if(parent.right != null)
                newStack.push(parent.right);

            oldStack.push(parent);
        }

        while(!oldStack.isEmpty()) {
            System.out.println(oldStack.pop().value);
        }
    }

    public void removeNode(int value) {
       Node nodeToDelete = findNode(value, rootNode);
       if(Objects.isNull(nodeToDelete))
           System.out.println("No node found for value: "+value);
       else
           removeNode(nodeToDelete);
    }

    private void removeNode(Node node) {
        Node parent = findParent(node);
        if(Objects.nonNull(parent)) {
            if(isLeafNode(node)) {
                reassignChildToParent(parent, node, null);
            }
            else if(Objects.isNull(node.left)) {
                reassignChildToParent(parent, node, node.right);
            }
            else if(Objects.isNull(node.right)) {
                reassignChildToParent(parent, node, node.left);
            }
            else {
                Node highestInLeft = findHighestInSubtree(node.left);
                int highestInLeftVal = highestInLeft.value;
                removeNode(highestInLeft);
                node.value = highestInLeftVal;
            }
        }
        else {
            if(isLeafNode(rootNode))
                rootNode = null;
            else if(Objects.isNull(rootNode.left))
                rootNode = rootNode.right;
            else if(Objects.isNull(rootNode.right))
                rootNode = rootNode.left;
            else {
                Node highestInLeft = findHighestInSubtree(node.left);
                int highestInLeftVal = highestInLeft.value;
                removeNode(highestInLeft);
                node.value = highestInLeftVal;
            }
        }
    }

    private void reassignChildToParent(Node parent, Node childToRemove, Node newChild) {
        if(parent.left == childToRemove)
            parent.left = newChild;
        else
            parent.right = newChild;
    }

    private Node findNode(int value, Node node) {
        if(Objects.nonNull(node)) {
            if(value == node.value)
                return node;
            else if(value < node.value)
                return findNode(value, node.left);
            else
                return findNode(value, node.right);
        }

        return null;
    }

    private Node findParent(Node node) {
        if(node == this.rootNode) return null;

        Node pointer = this.rootNode;
        Node parentPointer = pointer;

        while(Objects.nonNull(pointer)) {
            if(node == pointer)
                break;
            else if(node.compareTo(pointer) < 0) {
                parentPointer = pointer;
                pointer = pointer.left;
            }
            else {
                parentPointer = pointer;
                pointer = pointer.right;
            }
        }
        return parentPointer;
    }

    private Node findHighestInSubtree(Node node) {
        if (node.right == null)
            return node;
        else
            return findHighestInSubtree(node.right);
    }

    private boolean isLeafNode(Node node) {
        return Objects.isNull(node.left) && Objects.isNull(node.right);
    }

    public boolean isSameTree(BinarySearchTree bst) {
        return isSameTree(this.rootNode, bst.rootNode);
    }

    private boolean isSameTree(Node tree1, Node tree2) {
        if(tree1 == null && tree2 == null)
            return true;

        if(tree1 == null || tree2 == null)
            return false;

        return tree1.equals(tree2)
                && isSameTree(tree1.left, tree2.left)
                && isSameTree(tree1.right, tree2.right);
    }

    public void printRightSideView() {
        if(this.rootNode==null)
            return;

        System.out.println("------------RIGHT SIDE VIEW OF TREE------------");
        Node node = this.rootNode;
        Queue<Node> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(node);
        int level = 0;

        while(!nodeQueue.isEmpty()) {
            Node temp = nodeQueue.poll();

        }
    }

    public int minDepth() {
        return minDepth(this.rootNode);
    }

    private int minDepth(Node node) {
        if(node == null)
            return 0;

        if(node.left == null && node.right == null)
            return 1;

        int leftDepth = (node.left != null) ? minDepth(node.left) : Integer.MAX_VALUE;
        int rightDepth = (node.right != null) ? minDepth(node.right) : Integer.MAX_VALUE;

        return 1 + Math.min(leftDepth, rightDepth);
    }

    public int maxDepth() {
        return maxDepth(this.rootNode);
    }

    private int maxDepth(Node node) {
        if(node == null)
            return 0;

        if(node.left == null && node.right == null)
            return 1;

        int leftDepth = (node.left != null) ? maxDepth(node.left) : Integer.MIN_VALUE;
        int rightDepth = (node.right != null) ? maxDepth(node.right) : Integer.MIN_VALUE;

        return 1 + Math.max(leftDepth, rightDepth);
    }

    public void printNodesWithoutSiblings() {
        if(this.rootNode == null)
            return;

        Node node = this.rootNode;
        Queue<Node> traversalQueue = new ArrayDeque<>();
        Queue<Node> noSiblings = new ArrayDeque<>();

        traversalQueue.offer(node);
        noSiblings.offer(node);

        while (!traversalQueue.isEmpty() || !noSiblings.isEmpty()) {
            if(!noSiblings.isEmpty()) {
                Node temp = noSiblings.poll();
                System.out.print(temp.value+" ");
            }

            if(!traversalQueue.isEmpty()) {
                Node temp = traversalQueue.poll();
                if(temp.left==null && temp.right!=null)
                    noSiblings.offer(temp.right);

                if(temp.right==null && temp.left!=null)
                    noSiblings.offer(temp.left);

                if(temp.left!=null)
                    traversalQueue.offer(temp.left);

                if(temp.right!=null)
                    traversalQueue.offer(temp.right);
            }
        }
    }

    public void printNodesWithoutSiblingsRecursion() {
        printNodesWithoutSiblingsRecursion(this.rootNode);
    }

    private void printNodesWithoutSiblingsRecursion(Node node) {
        if(node == null)
            return;

        if(node.left==null && node.right!=null)
            System.out.print(node.right.value + " ");

        if(node.right==null && node.left!=null)
            System.out.print(node.left.value + " ");

        printNodesWithoutSiblingsRecursion(node.left);
        printNodesWithoutSiblingsRecursion(node.right);
    }

    public void mirrorTree() {
        mirrorTree(this.rootNode);
    }

    private void mirrorTree(Node node) {
        if(node == null)
            return;

        if(node.left == null && node.right == null)
            return;

        Node temp = node.left;
        node.left = node.right;
        node.right = temp;

        mirrorTree(node.left);
        mirrorTree(node.right);
    }

    public int countNodes() {
        return countNodes(this.rootNode);
    }

    private int countNodes(Node node) {
        if(node == null)
            return 0;

        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public void printRootToLeaves() {
        printRootToLeaves(this.rootNode, new ArrayDeque<>());
    }

    private void printRootToLeaves(Node node, Deque<Node> nodeList) {
        if(node == null)
            return;

        nodeList.add(node);
        if(node.left == null && node.right == null) {
            nodeList.forEach(printNode -> System.out.print(printNode.value+" "));
            System.out.println();
            return;
        }

        List<Integer> list = new ArrayList<>();
        list.stream().mapToInt(Integer::intValue).sum();
        printRootToLeaves(node.left, nodeList);
        nodeList.remove(node.left);
        printRootToLeaves(node.right, nodeList);
        nodeList.remove(node.right);
    }

    public void getRootToLeafPaths() {
        List<Deque<Integer>> paths = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();

        getRootToLeafPaths(rootNode, path, paths);
        for(Deque<Integer> iPath : paths) {
            System.out.println(iPath.toString());
        }
    }

    private void getRootToLeafPaths(Node root,
                                    Deque<Integer> path, List<Deque<Integer>> paths) {
        if(root == null)
            return;

        path.addLast(root.value);
        if(root.left == null && root.right == null) {
            paths.add(new ArrayDeque<>(path));
            return;
        }

        if(root.left != null) {
            getRootToLeafPaths(root.left, path, paths);
            path.removeLast();
        }

        if(root.right != null) {
            getRootToLeafPaths(root.right, path, paths);
            path.removeLast();
        }
    }

    public void replaceHalfNodes() {
        replaceHalfNodes(this.rootNode);
    }

    private void replaceHalfNodes(Node node) {
        if(node == null)
            return;

        if(node.left == null && node.right == null)
            return;

        if(node.left != null && node.right != null) {
            replaceHalfNodes(node.left);
            replaceHalfNodes(node.right);
        }

        if(node.left==null && node.right!=null) {
            Node parent = findParent(node);
            if(parent == null) {
                this.rootNode = findPerfectNodeForReplacement(node.right);
                replaceHalfNodes(this.rootNode);
            }
            else {
                Node replacement = findPerfectNodeForReplacement(node.right);
                if(parent.left == node)
                    parent.left = replacement;
                else
                    parent.right = replacement;
                replaceHalfNodes(replacement);
            }
        }

        if(node.right==null && node.left != null) {
            Node parent = findParent(node);
            if(parent == null) {
                this.rootNode = findPerfectNodeForReplacement(node.left);
                replaceHalfNodes(this.rootNode);
            }
            else {
                Node replacement = findPerfectNodeForReplacement(node.left);
                if(parent.left == node)
                    parent.left = replacement;
                else
                    parent.right = replacement;
                replaceHalfNodes(replacement);
            }
        }
    }

    private Node findPerfectNodeForReplacement(Node node) {
        if(node == null)
            return null;

        if(node.left != null && node.right != null)
            return node;

        if(node.left == null && node.right == null)
            return node;

        Node left = findPerfectNodeForReplacement(node.left);
        return (left != null) ? left : findPerfectNodeForReplacement(node.right);
    }

    public List<Integer> inorderToList() {
        List<Integer> integers = new ArrayList<>();
        inorderToList(rootNode, integers);
        return integers;
    }

    private void inorderToList(Node node, List<Integer> list) {
        if(node != null) {
            inorderToList(node.left, list);
            list.add(node.value);
            inorderToList(node.right, list);
        }
    }

    public void rightViewOfTree(Node node) {
        if(node == null)
            return;

        Queue<Node> queue = new ArrayDeque<>();
        Map<Integer, Integer> tracker = new HashMap<>();

        int level = 0;
        queue.add(node);
        tracker.put(level, 1);

        while(!queue.isEmpty()) {
            Node bnode = queue.poll();

            int nodesInLevel = tracker.getOrDefault(level+1, 0);
            if(bnode.left != null) {
                queue.offer(bnode.left);
                nodesInLevel++;
            }

            if(bnode.right != null) {
                queue.offer(bnode.right);
                nodesInLevel++;
            }

            tracker.put(level+1, nodesInLevel);

            if(tracker.get(level) == 1) {
                System.out.print(bnode.value+" ");
                tracker.put(level, 0);
                level++;
            }
            else {
                int visited = tracker.get(level);
                tracker.put(level, visited - 1);
            }
        }
    }

    public int lowestCommonAncestor(int a, int b) {
        List<Integer> listA = getRouteNodes(a, rootNode, new ArrayList<>());
        List<Integer> listB = getRouteNodes(b, rootNode, new ArrayList<>());

        List<Integer> smallerRoute = listA, biggerRoute = listB;
        if(listA.size() > listB.size()) {
            smallerRoute = listB;
            biggerRoute = listA;
        }

        int j=-1, i=0;
        while(i<smallerRoute.size()) {
            if(smallerRoute.get(i) != biggerRoute.get(i)) {
                break;
            }
            i++;
            j++;
        }

        if(j == -1)
            return -1;

        if(smallerRoute.get(j) == biggerRoute.get(j))
            return smallerRoute.get(j);

        return -1;
    }

    private List<Integer> getRouteNodes(int val, Node tracker, List<Integer> route) {
        if(tracker == null)
            return route;

        route.add(tracker.value);
        if(val == tracker.value)
            return route;
        else if(val < tracker.value)
            return getRouteNodes(val, tracker.left, route);
        else
            return getRouteNodes(val, tracker.right, route);
    }

    public int lca(int a, int b) {
        Node node = (a < b) ? _lca(rootNode, a, b) : _lca(rootNode, b, a);
        return node == null ? Integer.MIN_VALUE : node.value;
    }

    private Node _lca(Node root, int small, int big) {
        if(root == null)
            return null;

        if(root.value == small || root.value == big || (root.value > small && root.value < big))
            return root;
        else if(root.value < small && root.value < big)
            return _lca(root.right, small, big);
        else
            return _lca(root.left, small, big);
    }

    public int maxDepthOfOddLevelLeafNode(Node node) {
        return _maxDepthOfOddLevelLeafNode(node, 0);
    }

    private int _maxDepthOfOddLevelLeafNode(Node node, int currentLevel) {
        if(node == null)
            return 0;

        if(node.left == null && node.right == null)
            return (currentLevel & 1) == 1 ? currentLevel : 0;

        int left = _maxDepthOfOddLevelLeafNode(node.left, currentLevel+1);
        int right = _maxDepthOfOddLevelLeafNode(node.right, currentLevel+1);

        return Math.max(left, right);
    }

    public Map<Integer, Integer> diagonalSum() {
        Map<Integer, Integer> diagonalSumMap = new HashMap<>();
        _diagonalSum(rootNode, 0, diagonalSumMap);
        return diagonalSumMap;
    }

    private void _diagonalSum(Node node, int sumLevel, Map<Integer, Integer> diagonalSumMap) {
        if(node == null)
            return;

        int sum = node.value + diagonalSumMap.getOrDefault(sumLevel, 0);
        diagonalSumMap.put(sumLevel, sum);
        _diagonalSum(node.right, sumLevel, diagonalSumMap);
        _diagonalSum(node.left, sumLevel+1, diagonalSumMap);
    }

    public boolean areCousins(int val1, int val2) {
        if(rootNode == null)
            return false;

        if(val1 == val2)
            return false;

        Tuple<Node, Node> t1 = findGrandFatherParentNode(val1);
        Tuple<Node, Node> t2 = findGrandFatherParentNode(val2);

        return (t1.get_1() != t2.get_1()) && (t1.get_2() == t2.get_2());
    }

    public int inorderSuccessor(int val) {
        return _inorderSuccessor(val, rootNode, null);
    }

    private int _inorderSuccessor(int val, Node node, Node parent) {
        if(node == null)
            return -1;

        if(val == node.value) {
            if(node.right != null) {
                Node successor = _findLowestInSubtree(node.right);
                return successor.value;
            }
            else {
                return (parent.value > node.value) ? parent.value : -1;
            }
        }
        else if(val < node.value) {
            return _inorderSuccessor(val, node.left, node);
        }
        else {
            if(parent != null && val < parent.value)
                return _inorderSuccessor(val, node.right, parent);

            return _inorderSuccessor(val, node.right, node);
        }
    }

    private Node _findLowestInSubtree(Node node) {
        if(node == null)
            return null;

        if(node.left == null)
            return node;

        return _findLowestInSubtree(node.left);
    }

    public Map<Integer, Integer> sumOfAllLevels(Node node) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        _sumOfAllLevels(rootNode, 0, sumMap);
        return sumMap;
    }

    private void _sumOfAllLevels(Node node, int currentLevel, Map<Integer, Integer> sumMap) {
        if(node == null)
            return;

        int sum = node.value + sumMap.getOrDefault(currentLevel, 0);
        sumMap.put(currentLevel, sum);

        _sumOfAllLevels(node.left, currentLevel+1, sumMap);
        _sumOfAllLevels(node.right, currentLevel+1, sumMap);
    }

    public void printTopCover() {
        if(rootNode == null)
            return;

        _printTopCover(rootNode.left, true);
        System.out.print(rootNode.value+" ");
        _printTopCover(rootNode.right, false);
    }

    private void _printTopCover(Node node, boolean isLeft) {
        if(node == null)
            return;

        if(isLeft) {
            if(node.left != null)
                _printTopCover(node.left, isLeft);
            else
                _printTopCover(node.right, isLeft);
            System.out.print(node.value+" ");
        }
        else {
            System.out.print(node.value+" ");
            if(node.right != null)
                _printTopCover(node.right, isLeft);
            else
                _printTopCover(node.left, isLeft);
        }
    }

    public Tuple<Node, Node> findGrandFatherParentNode(int val) {
        return _findGrandFatherParentNode(val, rootNode, null, null);
    }

    private Tuple<Node, Node> _findGrandFatherParentNode(int val, Node node, Node parent, Node grandFather) {
        if(val == node.value)
            return new Tuple<>(parent, grandFather);

        if(val < node.value)
            return _findGrandFatherParentNode(val, node.left, node, parent);

        return _findGrandFatherParentNode(val, node.right, node, parent);
    }

    public void binarySearchAdd(int[] arr) {
        _binarySearchAdd(arr, 0, arr.length-1);
    }

    private void _binarySearchAdd(int[] arr, int begin, int end) {
        if(begin > end)
            return;

        int mid = (begin+end)/2;
        addNode(arr[mid]);

        _binarySearchAdd(arr, begin, mid-1);
        _binarySearchAdd(arr, mid+1, end);
    }

    public void binarySearchAddDoublyLinkedList(DoublyLinkedList<Integer> list) {
        int len = list.length();
        _binarySearchAddDoublyLinkedList(list.getHead(), 0, len-1);
    }

    private void _binarySearchAddDoublyLinkedList(DoublyLinkedListNode<Integer> head, int begin, int end) {
        if(begin > end)
            return;

        int mid = (begin+end)/2;
        DoublyLinkedListNode<Integer> ptr = head;
        int counter = 0;
        while(counter < mid && ptr != null) {
            ptr = ptr.getNext();
            counter++;
        }

        if(ptr != null) {
            addNode(ptr.getVal());
        }

        _binarySearchAddDoublyLinkedList(head, begin, mid-1);
        _binarySearchAddDoublyLinkedList(head, mid+1, end);
    }

    public int minDiffRecursive(Node node) {
        if(node == null)
            return Integer.MAX_VALUE;

        Node left = getHighest(node.left);
        Node right = getLowest(node.right);
        int min = Integer.MAX_VALUE;

        if(left != null)
            min = node.value - left.value;

        if(right != null && (min > (right.value - node.value))) {
            min = right.value - node.value;
        }

        return Math.min(Math.min(min, minDiffRecursive(node.left)), minDiffRecursive(node.right));
    }

    private Node getHighest(Node root) {
        if(root == null)
            return null;

        if(root.right == null)
            return root;

        return getHighest(root.right);
    }

    private Node getLowest(Node root) {
        if(root == null)
            return null;

        if(root.left == null)
            return root;

        return getLowest(root.left);
    }


    public static long uniqueBstCombinations(int n) {
        long[] cache = new long[n+1];
        for(int i=0; i<=n; i++)
            cache[i] = -1;

        return numTrees(n, cache);
    }

    private static long numTrees(int n, long[] cache) {
        if(n == 0 || n == 1)
            return 1;

        long numPossibilities = 0;
        for(int i=1; i<=n; i++) {
            if(cache[i-1] == -1)
                cache[i-1] = numTrees(i-1, cache);

            if(cache[n-i] == -1)
                cache[n-i] = numTrees(n-i, cache);

            numPossibilities += cache[i-1]*cache[n-i];
        }

        return numPossibilities;
    }

    public List<Double> averageOfLevels() {
        return _averageOfLevels(this.rootNode);
    }

    private List<Double> _averageOfLevels(Node rootNode) {
        if(rootNode == null)
            return Collections.emptyList();

        Queue<Node> nodeQueue = new LinkedList<>();
        List<Double> avgList = new ArrayList<>();

        nodeQueue.add(rootNode);
        int nodeCount = 1;
        int numNodes = 1;
        int sum = 0;

        int nextNodeCount = 0;

        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.poll();

            if(node.left != null) {
                nextNodeCount++;
                nodeQueue.add(node.left);
            }

            if(node.right != null) {
                nextNodeCount++;
                nodeQueue.add(node.right);
            }

            sum += node.value;
            nodeCount--;

            if(nodeCount == 0 && numNodes != 0) {
                double avg = (double) sum/numNodes;
                avgList.add(avg);
                sum = 0;
                nodeCount = nextNodeCount;
                numNodes = nextNodeCount;
                nextNodeCount = 0;
            }
        }

        return avgList;
    }

    static class LevelItem {
        private double currentAverage;
        private int numNodes;

        public LevelItem(double currentAverage, int numNodes) {
            this.currentAverage = currentAverage;
            this.numNodes = numNodes;
        }

        public double getCurrentAverage() {
            return currentAverage;
        }

        public void calculateNewAverage(int newVal) {
            currentAverage = ((numNodes * currentAverage) + newVal)/(numNodes+1);
            numNodes++;
        }
    }

    public List<Double> findAverageOfLevelsDfs(Node root) {
        if(root == null)
            return Collections.emptyList();

        Map<Integer, LevelItem> map = new HashMap<>();
        _findAverageOfLevelsDfs(root, map, 0);

        return map
                .values()
                .stream()
                .map(LevelItem::getCurrentAverage)
                .collect(Collectors.toList());
    }

    private void _findAverageOfLevelsDfs(Node root, Map<Integer, LevelItem> avgMap,
                                        int level) {
        if(root == null)
            return;

        if(avgMap.containsKey(level)) {
            LevelItem levelItem = avgMap.get(level);
            levelItem.calculateNewAverage(root.value);
            avgMap.put(level, levelItem);
        }
        else {
            avgMap.put(level, new LevelItem(root.value, 1));
        }

        _findAverageOfLevelsDfs(root.left, avgMap, level+1);
        _findAverageOfLevelsDfs(root.right, avgMap, level+1);
    }

    private List<Double> findAverageOfLevelsBfs(Node root) {
        if(root == null)
            return Collections.emptyList();

        Queue<Node> bfsQ = new LinkedList<>();
        List<Double> averages = new ArrayList<>();
        int level = 0;
        bfsQ.add(root);

        while(!bfsQ.isEmpty()) {
            int size = bfsQ.size();
            double sum = 0.0;

            for(int i=0; i<size; i++) {
                Node node = bfsQ.poll();

                if(node.left != null)
                    bfsQ.add(node.left);

                if(node.right != null)
                    bfsQ.add(node.right);

                sum += node.value;
            }

            averages.add(level, sum/size);
            level++;
        }

        return averages;
    }

    public Node buildTree(int[] preorder) {
        return _buildTree(preorder, 0, preorder.length-1);
    }

    private Node _buildTree(int[] preorder, int begin, int end) {
        if(begin > end)
            return null;

        int rootVal = preorder[begin];
        int mid = begin;
        while(mid <= end) {
            if(preorder[mid] > rootVal)
                break;

            mid++;
        }

        Node node = new Node(rootVal);
        node.left = _buildTree(preorder, begin+1, mid-1);
        node.right = _buildTree(preorder, mid, end);

        return node;
    }

    private int idx = 0;

    public Node formBst(int[] inorderRepresentation) {
        if(idx != 0) idx = 0;
        return _formBst(inorderRepresentation, 0, inorderRepresentation.length-1);
    }

    private Node _formBst(int[] inorderRepresentation, int start, int end) {
        if(start > end)
            return null;

        int mid = (start + end)/2;

        Node node = new Node(0);
        node.left = _formBst(inorderRepresentation, start, mid - 1);
        node.value = inorderRepresentation[idx++];
        node.right = _formBst(inorderRepresentation, mid+1, end);

        return node;
    }

    Node tempNode = null;

    public void convertToSortedLinkedList() {
        _convertToSortedLinkedList(rootNode);
        rootNode = tempNode;
    }

    private void _convertToSortedLinkedList(Node root) {
        if(root == null)
            return;

        _convertToSortedLinkedList(root.right);
        root.right = tempNode;
        tempNode = root;
        _convertToSortedLinkedList(root.left);
        root.left = null;
    }

    public Node convertToSortedDoubleLinkedList() {
        rootNode = _convertToSortedDoubleLinkedList(rootNode);
        while (rootNode != null) {
            if(rootNode.left == null)
                break;

            rootNode = rootNode.left;
        }

        return rootNode;
    }

    private Node _convertToSortedDoubleLinkedList(Node root) {
        if(root == null)
            return null;

        Node left = _convertToSortedDoubleLinkedList(root.left);
        while (left != null) {
            if(left.right == null) {
                left.right = root;
                break;
            }
            left = left.right;
        }
        root.left = left;

        Node right = _convertToSortedDoubleLinkedList(root.right);
        while (right != null) {
            if(right.left == null) {
                right.left = root;
                break;
            }
            right = right.left;
        }
        root.right = right;

        return root;
    }

    private void _rootToLeafPath(Node root, List<Integer> list, int idx, List<List<Integer>> paths) {
        if(root == null) {
            return;
        }

        list.add(idx, root.value);

        if(root.left == null && root.right == null) {
            paths.add(new ArrayList<>(list));
            return;
        }

        if(root.left != null) {
            _rootToLeafPath(root.left, list, idx+1, paths);
            list.remove(idx+1);
        }

        if(root.right != null) {
            _rootToLeafPath(root.right, list, idx+1, paths);
            list.remove(idx+1);
        }
    }

    public void removeNodesWithPathSumLessThan(int k) {
        int rootSum = _removeNodesWithPathSumLessThan(rootNode, 0, k);

        if(rootSum < k)
            rootNode = null;
    }

    private int _removeNodesWithPathSumLessThan(Node node, int sum, int k) {
        if(node == null) {
            return sum;
        }

        int leftSum = _removeNodesWithPathSumLessThan(node.left, sum+node.value, k);
        int rightSum = _removeNodesWithPathSumLessThan(node.right, sum+node.value, k);

        if(leftSum < k)
            node.left = null;
        if(rightSum < k)
            node.right = null;

        if(leftSum >= k)
            return leftSum;
        else if(rightSum >= k)
            return rightSum;
        else
            return sum+node.value;
    }

    public boolean isBalanced() {
        return (_isBalanced(rootNode) == -1) ? false : true;
    }

    private int _isBalanced(Node node) {
        if(node == null)
            return 0;

        int left = _isBalanced(node.left);
        int right = _isBalanced(node.right);

        if(left == -1 || right == -1 || Math.abs(left - right) >= 2)
            return -1;

        return left > right ? 1 + left : 1 + right;
    }

    private boolean _isBalancedMinHeight(Node node) {
        if(node == null)
            return true;

        return _isBalancedMinHeight(node.left)
                && _isBalancedMinHeight(node.right)
                && Math.abs(minHeight(node.left) - minHeight(node.right)) <= 2;
    }

    private int minHeight(Node node) {
        if(node == null)
            return 0;

        int left = minHeight(node.left);
        int right = minHeight(node.right);

        return (left == 0 || right == 0) ? 1 + left + right : 1 + Math.min(left, right);
    }
}
