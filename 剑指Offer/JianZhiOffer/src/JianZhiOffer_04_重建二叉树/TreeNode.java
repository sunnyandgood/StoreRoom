package JianZhiOffer_04_重建二叉树;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        /**
         * 中序遍历
         */
        StringBuffer buffer = new StringBuffer("");
        if (this.left != null){
            buffer.append(left);
        }
        buffer.append(val);
        if (this.right != null){
            buffer.append(right);
        }
        return buffer.toString();
    }
}
