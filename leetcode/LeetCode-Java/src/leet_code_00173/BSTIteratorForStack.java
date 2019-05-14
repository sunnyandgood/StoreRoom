package leet_code_00173;

import java.util.LinkedList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/14 23:43
 */
public class BSTIteratorForStack {
    private LinkedList<TreeNode> stack = new LinkedList<>();

    public BSTIteratorForStack(TreeNode root) {
        // 初始化时把所有左子树压入栈
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        // 栈顶节点即为当前最小节点
        TreeNode smallestNode = stack.pop();
        int val = smallestNode.val;
        // 出栈后需要将其右子树及其右子树的左子树压入栈
        smallestNode = smallestNode.right;
        while (smallestNode != null) {
            stack.push(smallestNode);
            smallestNode = smallestNode.left;
        }
        return val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 *
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 *      7
 *    /   \
 *   3    15
 *        / \
 *       9   20
 *
 * 示例：
 *
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *
 *
 *
 * 提示：
 *     next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 *     你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 */