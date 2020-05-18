package ramesh.aadhavan.tree;

import java.util.List;
import java.util.Optional;

public interface BalancedBinaryTree {
    void add(Integer val);
    void delete(Integer val);
    int height();
    Optional<Node> getRoot();
    List<Node> preorder();
    List<Node> postorder();
    List<Node> inorder();
}
