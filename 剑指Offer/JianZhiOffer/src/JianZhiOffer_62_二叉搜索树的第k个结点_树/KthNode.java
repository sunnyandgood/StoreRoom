package JianZhiOffer_62_二叉搜索树的第k个结点_树;

/**
 * @Author: sunnyandgood
 * @Date: 2019/9/8 0:54
 * 62、二叉搜索树的第k个结点
 * 题目描述：
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */
public class KthNode {
    private int k;

    TreeNode KthNode(TreeNode pRoot, int k) {
        this.k = k;
        return kthNode(pRoot);
    }

    TreeNode kthNode(TreeNode pRoot) {
        if (pRoot == null || k == 0) return null;
        // 遍历到最左节点
        if (pRoot.left != null) {
            TreeNode left = KthNode(pRoot.left, k);
            if (left != null) {
                return left;
            }
        }
        // 遍历pRoot
        k--;
        // 遍历右子树
        if (pRoot.right != null) {
            TreeNode right = KthNode(pRoot.right, k);
            if (right != null) {
                return right;
            }
        }
        return k == 0 ? pRoot : null;
    }

    TreeNode KthNode_1(TreeNode pRoot, int k)
    {
        if(pRoot == null || k <= 0){
            return null;
        }
        TreeNode[] result = new TreeNode[1];
        KthNode(pRoot,k,new int[1],result);
        return result[0];
    }
    void KthNode(TreeNode pRoot, int k,int[] count,TreeNode[] result){
        if(result[0] != null || pRoot == null){
            return;
        }
        KthNode(pRoot.left,k,count,result);
        count[0]++;
        if(count[0] == k){
            result[0] = pRoot;
        }
        KthNode(pRoot.right,k,count,result);
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