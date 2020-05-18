package ramesh.aadhavan.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SerializeDeserializeWithDuplicates {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        _preorder(root, preorder);

        return preorder.stream()
                .map(i -> i == null ? "null" : Integer.toString(i))
                .reduce((acc, x) -> String.join(" ", acc, x))
                .orElse("");
    }

    private void _preorder(TreeNode root, List<Integer> list) {
        if(root==null) {
            list.add(null);
            return;
        }

        list.add(root.val);
        _preorder(root.left, list);
        _preorder(root.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isBlank() || data.equals("null"))
            return null;

        Integer[] preorder = Arrays.stream(data.split(" "))
                .map(s -> s.equals("null") ? null : Integer.parseInt(s))
                .toArray(Integer[]::new);

        final AtomicInteger atomicInteger = new AtomicInteger(0);
        return _reconstruct(preorder, atomicInteger);
    }

    private TreeNode _reconstruct(Integer[] data, AtomicInteger idx) {
        if(idx.get() >= data.length || data[idx.get()] == null) {
            idx.incrementAndGet();
            return null;
        }

        TreeNode node = new TreeNode(data[idx.get()]);
        idx.incrementAndGet();
        node.left = _reconstruct(data, idx);
        node.right = _reconstruct(data, idx);

        return node;
    }
}
