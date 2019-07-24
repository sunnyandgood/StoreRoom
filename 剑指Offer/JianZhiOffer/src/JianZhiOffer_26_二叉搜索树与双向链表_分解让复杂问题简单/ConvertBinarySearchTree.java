package JianZhiOffer_26_二叉搜索树与双向链表_分解让复杂问题简单;

import java.util.Stack;

/**
 * @Author: sunnyandgood
 * @Date: 2019/7/22 23:05
 * 26、二叉搜索树与双向链表
 * 题目描述：
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class ConvertBinarySearchTree {
    /**public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        TreeNode pLastNodeInList = null;
        convertNode(pRootOfTree,pLastNodeInList);
        //pLastNodeInList指向双向链表
        //需要返回头结点
//        TreeNode pHeadOfList = pLastNodeInList;
        while (pLastNodeInList != null && pLastNodeInList.left != null){
            pLastNodeInList = pLastNodeInList.left;
        }
        return pLastNodeInList;
    }

    private void convertNode(TreeNode pNode,TreeNode pLastNoeInList){
        if (pNode == null){
            return;
        }
        TreeNode pCurrent = pNode;
        if (pCurrent.left != null){
            convertNode(pCurrent.left,pLastNoeInList);
        }
        pCurrent.left = pLastNoeInList;
        if (pLastNoeInList != null){
            pLastNoeInList.right = pCurrent;
        }
        pLastNoeInList = pCurrent;
        if (pCurrent.right != null){
            convertNode(pCurrent.right,pLastNoeInList);
        }
    }*/
    public TreeNode Convert(TreeNode root) {
        if(root == null)
            return null;
        if(root.left == null&&root.right == null)
            return root;
        TreeNode left=Convert(root.left);
        TreeNode p=left;
        while(p != null&&p.right != null) {
            p=p.right;
        }
        if(left != null) {
            p.right=root;
            root.left=p;
        }
        TreeNode right=Convert(root.right);
        if(right!=null) {
            root.right=right;
            right.left=root;
        }
        return  left!=null?left:root;
    }


    public TreeNode Convert1(TreeNode pRootOfTree) {
        if(pRootOfTree == null){
            return null;
        }
        TreeNode node = pRootOfTree;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Connection(node,stack);

        node = stack.get(0);
        return node;
    }
    private void Connection(TreeNode newNode,Stack<TreeNode> stack){
        if(newNode == null) {
            return;
        }
        Connection(newNode.left,stack);
        if(stack.isEmpty()){
            stack.push(newNode);
        }
        else{
            stack.peek().right = newNode;
            newNode.left = stack.peek();
            stack.push(newNode);
        }
        Connection(newNode.right,stack);
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