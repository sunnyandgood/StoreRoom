package leet_code_00226_翻转二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/13 23:54
 * 226. 翻转二叉树
 */
public class Solution {
    //先序遍历：根左右
    public TreeNode invertTree1(TreeNode root) {
        if (root==null){
            return null;
        }else {
            // 反转左右子树
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            // 递归反转左右子树
            invertTree1(root.left);
            invertTree1(root.right);
            return root;
        }
    }

    //后序遍历：左右根
    public TreeNode invertTree2(TreeNode root){
        if (root==null){
            return null;
        }else {
            // 递归反转左右子树
            invertTree2(root.left);
            invertTree2(root.right);
            // 反转左右子树
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            return root;
        }
    }

    //中序遍历：左根右
    public TreeNode invertTree3(TreeNode root) {
        if (root==null){
            return null;
        }else {
            // 递归反转左节点的左右子树
            invertTree3(root.left);
            // 反转左右子树
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            // 递归反转右节点的左右子树
            invertTree3(root.left);
            return root;
        }
    }

    //层序遍历
    public TreeNode invertTree4(TreeNode root) {
        if (root==null){
            return null;
        }else {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
            }
            return root;
        }
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

/**
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 输入
 * [4,2,7,1,3,6,9]
 * 输出
 * [4,7,2,9,6,3,1]
 */