package leet_code_00173;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/14 23:28
 * 173. 二叉搜索树迭代器
 */
public class BSTIteratorForList {
    private Iterator<Integer> iterator;

    public BSTIteratorForList(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inorderTraversal(list,root);
        iterator = list.iterator();
    }

    /**
     * 中序遍历：左根右
     * @param list
     * @param node
     */
    private void inorderTraversal(ArrayList<Integer> list, TreeNode node){
        if (node!=null){
            inorderTraversal(list,node.left);
            list.add(node.val);
            inorderTraversal(list,node.right);
        }
    }

    /** @return the next smallest number */
    public int next() {
        return iterator.next();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return iterator.hasNext();
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