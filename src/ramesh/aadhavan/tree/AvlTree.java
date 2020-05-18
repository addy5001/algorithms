package ramesh.aadhavan.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class AvlTree implements BalancedBinaryTree {

    private class AvlNode implements Node {
        Integer val;
        AvlNode left;
        AvlNode right;
        int height;

        public AvlNode(Integer val) {
            this.val = val;
            this.height = 1;
        }

        @Override
        public Integer getValue() {
            return val;
        }
    }

    private AvlNode root;

    public AvlTree() {
        this.root = null;
    }

    @Override
    public Optional<Node> getRoot() {
        return Optional.ofNullable(root);
    }

    @Override
    public void add(Integer val) {
        root = _add(root, val);
    }

    private AvlNode _add(AvlNode root, Integer val) {
        if(root == null) {
            return new AvlNode(val);
        }
        else if(val < root.val) {
            root.left = _add(root.left, val);
        }
        else {
            root.right = _add(root.right, val);
        }

        _updateHeight(root);
        int balance = _getBalance(root);
        if(Math.abs(balance) >= 2) {
            if(balance > 0) {
                if(val < root.left.val) {
                    return _rotateRight(root);
                }
                else {
                    root.left = _rotateLeft(root.left);
                    return _rotateRight(root);
                }
            }
            else {
                if(val > root.right.val) {
                     return _rotateLeft(root);
                }
                else {
                    root.right = _rotateRight(root.right);
                    return _rotateLeft(root);
                }
            }
        }

        return root;
    }

    @Override
    public void delete(Integer val) {
        root = _delete(root, val);
    }

    private AvlNode _delete(AvlNode root, Integer val) {
        if(root == null) {
            return null;
        }

        if(val < root.val) {
            root.left = _delete(root.left, val);
        }
        else if(val > root.val) {
            root.right = _delete(root.right, val);
        }
        else {
            if(root.left == null) {
                root = root.right;
            }
            else if(root.right == null) {
                root = root.left;
            }
            else {
                Integer min = _findMin(root.right);
                root.val = min;
                root.right = _delete(root.right, min);
            }
        }

        _updateHeight(root);
        int balance = _getBalance(root);
        if(Math.abs(balance) >= 2) {
            if(balance > 0) {
                int leftBalance = _getBalance(root.left);
                if(leftBalance < 0) {
                    root.left = _rotateLeft(root.left);
                    root = _rotateRight(root);
                }
                else {
                    root = _rotateRight(root);
                }
            }
            else {
                int rightBalance = _getBalance(root.right);
                if(rightBalance <= 0) {
                    root = _rotateLeft(root);
                }
                else {
                    root.right = _rotateRight(root.right);
                    root = _rotateLeft(root);
                }
            }
        }

        return root;
    }

    private Integer _findMin(AvlNode node) {
        if(node == null)
            return Integer.MIN_VALUE;
        else if(node.left == null)
            return node.val;
        else
            return _findMin(node.left);
    }


    @Override
    public int height() {
        return root.height;
    }

    @Override
    public List<Node> preorder() {
        List<Node> preorderList = new ArrayList<>();
        _preorder(root, preorderList::add);
        return preorderList;
    }

    private void _preorder(AvlNode node, Consumer<Node> action) {
        if(root != null) {
            action.accept(node);
            _preorder(node.left, action);
            _preorder(node.right, action);
        }
    }

    @Override
    public List<Node> postorder() {
        return null;
    }

    @Override
    public List<Node> inorder() {
        return null;
    }

    private AvlNode _rotateLeft(AvlNode node) {
        if(node == null)
            return null;

        AvlNode rightChild = node.right;
        AvlNode leftGrandChild = rightChild.left;

        node.right = leftGrandChild;
        rightChild.left = node;

        _updateHeight(node);
        _updateHeight(rightChild);

        return rightChild;
    }

    private AvlNode _rotateRight(AvlNode node) {
        if(node == null)
            return null;

        AvlNode leftChild = node.left;
        AvlNode rightGrandChild = leftChild.right;

        node.left = rightGrandChild;
        leftChild.right = node;

        _updateHeight(node);
        _updateHeight(leftChild);

        return leftChild;
    }

    private void _updateHeight(AvlNode node) {
        if(node == null)
            return;

        node.height = 1 + Math.max(_getHeight(node.left), _getHeight(node.right));
    }

    private int _getBalance(AvlNode node) {
        if(node == null)
            return 0;

        return _getHeight(node.left) - _getHeight(node.right);
    }

    private int _getHeight(AvlNode node) {
        if(node == null)
            return 0;

        return node.height;
    }
}
