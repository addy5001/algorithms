package ramesh.aadhavan.tree;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(BinarySearchTree.Node root) {
        StringJoiner stringJoiner = new StringJoiner("/");
        stringJoiner.setEmptyValue("");
        _serialize(root, stringJoiner);
        return stringJoiner.toString();
    }

    private void _serialize(BinarySearchTree.Node root, StringJoiner sj) {
        if(root != null) {
            sj.add(Integer.toString(root.getValue()));
            _serialize(root.getLeft(), sj);
            _serialize(root.getRight(), sj);
        }
    }

    // Decodes your encoded data to tree.
    public BinarySearchTree.Node deserialize(String data) {
        if(data.equals(""))
            return null;

        String[] tokens = data.split("/");
        List<Integer> integerList = Arrays.stream(tokens).map(Integer::parseInt).collect(Collectors.toList());
        return _deserialize(integerList, 0, integerList.size()-1);
    }

    private int index = 0;

    private BinarySearchTree.Node _deserialize(List<Integer> preorder, int begin, int end) {
        if(begin > end)
            return null;

        int rootVal = preorder.get(begin);
        int i = begin+1;
        while(i <= end) {
            if(preorder.get(i) > rootVal)
                break;

            i++;
        }

        BinarySearchTree.Node node = new BinarySearchTree.Node(rootVal);
        node.setLeft(_deserialize(preorder, begin+1, i-1));
        node.setRight(_deserialize(preorder, i, end));

        return node;
    }

    private BinarySearchTree.Node _deserializeWithBound(List<Integer> preorder, int bound) {
        if(index == preorder.size() || preorder.get(index) > bound)
            return null;

        BinarySearchTree.Node node = new BinarySearchTree.Node(preorder.get(index++));
        node.setLeft(_deserializeWithBound(preorder, node.getValue()));
        node.setRight(_deserializeWithBound(preorder, bound));

        return node;
    }
}
