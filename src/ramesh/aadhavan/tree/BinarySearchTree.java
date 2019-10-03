package ramesh.aadhavan.tree;

import ramesh.aadhavan.list.LinkedList;

import java.util.*;

public class BinarySearchTree {

    public static class Node implements Comparable<Node> {
        private int value;
        private Node left;
        private Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public int getValue() {
            return value;
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
        inorder(this.rootNode);
    }

    private void inorder(Node node) {
        if(Objects.nonNull(node)) {
            inorder(node.left);
            System.out.print(node.value+" ");
            inorder(node.right);
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
        postorder(this.rootNode);
    }

    private void postorder(Node node) {
        if(Objects.nonNull(node)) {
            preorder(node.left);
            preorder(node.right);
            System.out.print(node.value+" ");
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
        printRootToLeaves(this.rootNode, new ArrayList<>());
    }

    private void printRootToLeaves(Node node, List<Node> nodeList) {
        if(node == null)
            return;

        nodeList.add(node);
        if(node.left == null && node.right == null) {
            nodeList.forEach(printNode -> System.out.print(printNode.value+" "));
            System.out.println();
            return;
        }

        printRootToLeaves(node.left, nodeList);
        nodeList.remove(node.left);
        printRootToLeaves(node.right, nodeList);
        nodeList.remove(node.right);
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

    public int lcaRecurse(int a, int b) {
        if(a < b)
            return lca(rootNode, rootNode, a, b, -1);
        else
            return lca(rootNode, rootNode, b, a, -1);
    }

    private int lca(Node trackerSmall, Node trackerBig, int smallVal, int bigVal, int lca) {
        if(trackerSmall == null || trackerBig == null)
            return -1;

        if(trackerSmall.value == smallVal && trackerBig.value == bigVal)
            return lca;

        if(trackerSmall == trackerBig) {
            if((trackerSmall.value == smallVal) || (trackerSmall.value == bigVal) ||
                    (smallVal < trackerSmall.value && bigVal > trackerSmall.value)) {
                lca = trackerSmall.value;
                if(trackerSmall.value == smallVal)
                    return lca(trackerSmall, trackerBig.right, smallVal, bigVal, lca);
                else if(trackerSmall.value == bigVal)
                    return lca(trackerSmall.left, trackerBig, smallVal, bigVal, lca);
                else
                    return lca(trackerSmall.left, trackerBig.right, smallVal, bigVal, lca);
            }
            else if(smallVal < trackerSmall.value) {
                return lca(trackerSmall.left, trackerBig.left, smallVal, bigVal, lca);
            }
            else {
                return lca(trackerSmall.right, trackerBig.right, smallVal, bigVal, lca);
            }
        }
        else {
            if(trackerSmall.value == smallVal && bigVal > trackerBig.value)
                return lca(trackerSmall, trackerBig.right, smallVal, bigVal, lca);
            else if(trackerBig.value == bigVal && smallVal < trackerSmall.value)
                return lca(trackerSmall.left, trackerBig, smallVal, bigVal, lca);
            else
                return lca(trackerSmall.left, trackerBig.right, smallVal, bigVal, lca);
        }
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
}
