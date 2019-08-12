package JianZhiOffer_38_二叉树的深度_知识迁移能力;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/12 22:46
 * 38、二叉树的深度
 * 题目描述：
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class TreeDepth {
    public int TreeDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return (left > right) ? (left + 1) : (right + 1);
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