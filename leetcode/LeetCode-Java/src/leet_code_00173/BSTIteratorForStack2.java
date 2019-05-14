package leet_code_00173;

import java.util.LinkedList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/15 0:19
 */
public class BSTIteratorForStack2 {
    private LinkedList<TreeNode> stack = new LinkedList<>();

    public BSTIteratorForStack2(TreeNode root) {
        addLeftPath(root);
    }

    private void addLeftPath(TreeNode node){
        while (node!=null){
            stack.push(node);
            node = node.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        addLeftPath(node.right);
        return node.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.isEmpty() ? false : true;
    }
}
