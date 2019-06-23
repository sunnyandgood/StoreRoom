package JianZhiOffer_18_二叉树的镜像_面试思路;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/23 10:23
 * 18、二叉树的镜像
 * 题目描述：
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 输入描述:
 * 二叉树的镜像定义：源二叉树
 *     	    8
 *     	   /  \
 *     	  6   10
 *     	 / \  / \
 *     	5  7 9 11
 *     	镜像二叉树
 *     	    8
 *     	   /  \
 *     	  10   6
 *     	 / \  / \
 *     	11 9 7  5
 * 解题思路：
 * 我们先前序遍历这棵树的每个结点，如果遍历到的结点有子结点，就交换它的两个子结点。
 * 当交换完所有非叶子结点的左右子结点之后，就得到了树的镜像。
 */
public class MirrorOfBinaryTree {
    public void Mirror(TreeNode root) {
        if (root == null || ((root.left == null) && (root.right == null))){
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (root.left != null){
            Mirror(root.left);
        }
        if (root.right != null){
            Mirror(root.right);
        }
    }
}
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}