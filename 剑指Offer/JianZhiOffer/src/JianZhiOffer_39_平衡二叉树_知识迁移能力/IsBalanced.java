package JianZhiOffer_39_平衡二叉树_知识迁移能力;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/12 23:41
 * 39、平衡二叉树
 * 题目描述：
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 任意节点的左右子树深度相差不超过1
 */
public class IsBalanced {
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null){
            return true;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int sub = left - right;
        if (sub > 1 || sub < -1){
            return false;
        }
        return true;
    }
    //求出左右子树的深度
    private int TreeDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return (left > right) ? (left + 1) : (right + 1);
    }


//    public boolean IsBalanced_Solution_1(TreeNode root) {
//        Integer depth = 0;
//        return isBalanced(root,depth);
//    }
//    private boolean isBalanced(TreeNode root,Integer depth){
//        if (root == null){
//            depth = 0;
//            return true;
//        }
//        Integer left = depth;
//        Integer right = depth;
//        if (isBalanced(root.left,left) && isBalanced(root.right,right)){
//            int diff = left - right;
//            if (diff <= 1 && diff >= -1){
//                depth = 1 + ((left > right) ? left : right);
//                return true;
//            }
//        }
//        return false;
//    }
}
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}