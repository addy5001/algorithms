package ramesh.aadhavan.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SerializeDeserializeTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null)
            return null;

        List<Integer> inorderList = new ArrayList<>();
        List<Integer> preorderList = new ArrayList<>();
        _inorder(root, inorderList);
        _preorder(root, preorderList);

        String inorderSer = inorderList.stream().map(integer -> Integer.toString(integer)).reduce((acc, x) -> String.join(" ", acc, x)).orElse("");
        String preorderSer = preorderList.stream().map(integer -> Integer.toString(integer)).reduce((acc, x) -> String.join(" ", acc, x)).orElse("");
        return String.join("|", preorderSer, inorderSer);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(Objects.isNull(data))
            return null;

        String[] ser = data.split("\\|");

        if(ser.length < 2)
            throw new IllegalArgumentException("Need preorder and inorder representations");

        String[] preorderSer = ser[0].split(" ");
        String[] inorderSer = ser[1].split(" ");

        if(preorderSer.length != inorderSer.length)
            throw new IllegalArgumentException("Preorder and Inorder representations should be of equal length");

        int[] preorder = new int[preorderSer.length];
        int[] inorder = new int[inorderSer.length];

        for(int i=0; i<preorderSer.length; i++) {
            preorder[i] = Integer.parseInt(preorderSer[i]);
            inorder[i] = Integer.parseInt(inorderSer[i]);
        }

        return _reconstruct(preorder, inorder);
    }

    private void _inorder(TreeNode root, List<Integer> list) {
        if(root==null)
            return;

        _inorder(root.left, list);
        list.add(root.val);
        _inorder(root.right, list);
    }

    private void _preorder(TreeNode root, List<Integer> list) {
        if(root==null)
            return;

        list.add(root.val);
        _preorder(root.left, list);
        _preorder(root.right, list);
    }

    private TreeNode _reconstruct(int[] preorder, int[] inorder) {
        return _build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    private TreeNode _build(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if(pStart > pEnd || iStart > iEnd)
            return null;

        int nodeVal = preorder[pStart];
        TreeNode node = new TreeNode(nodeVal);

        int iMid=iStart;
        while(iMid < iEnd) {
            if(inorder[iMid] == nodeVal) {
                break;
            }

            iMid++;
        }

        int leftElements = iMid - iStart;

        node.left = _build(preorder, pStart+1, pStart+leftElements, inorder, iStart, iMid-1);
        node.right = _build(preorder, pStart+leftElements+1, pEnd, inorder, iMid+1, iEnd);

        return node;
    }
}
