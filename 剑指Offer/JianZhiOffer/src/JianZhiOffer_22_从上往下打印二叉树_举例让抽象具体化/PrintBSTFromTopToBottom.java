package JianZhiOffer_22_从上往下打印二叉树_举例让抽象具体化;

import java.util.ArrayList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/7/14 22:42
 * 22、从上往下打印二叉树
 * 题目描述：
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * 解题思路:
 * 每一次打印一个结点的时候，如果该结点有子结点，则把该结点的子结点放到一个队列的末尾。
 * 接下来到队列的头部取出最早进入队列的结点，重复前面的打印操作，直至队列中所有的结点都被打印出来为止。
 */
public class PrintBSTFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode treeNode = queue.get(0);
            list.add(treeNode.val);
            queue.remove(treeNode);
            if (treeNode.left != null){
                queue.add(treeNode.left);
            }
            if (treeNode.right != null){
                queue.add(treeNode.right);
            }
        }
        return list;
    }
}
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        //先序遍历
        StringBuffer buffer = new StringBuffer("");
        buffer.append(val);
        if (this.left != null){
            buffer.append(this.left);
        }
        if (this.right != null){
            buffer.append(this.right);
        }
        return buffer.toString();
    }

    /**
     * 先序非递归创建
     */
    public void createTree(Integer[] treeNodes) {
        TreeNode root = new TreeNode(treeNodes[0]);
        for (int i = 1;i < treeNodes.length;i++){
            if (!treeNodes[i].equals("#")){

            }
        }

    }
}