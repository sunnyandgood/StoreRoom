package JianZhiOffer_59_按之字形顺序打印二叉树_树;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author: sunnyandgood
 * @Date: 2019/9/8 0:33
 * 59、按之字形顺序打印二叉树
 * 题目描述：
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class PrintBinaryTree {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> aList = new ArrayList<ArrayList<Integer>>();
        if(pRoot == null)
            return aList;

        Stack<TreeNode> s1 = new Stack<TreeNode>();
        s1.add(pRoot);
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        while(!s1.isEmpty()||!s2.isEmpty()){
            if(!s1.isEmpty()){
                ArrayList<Integer> aList2 = new ArrayList<Integer>();
                while(!s1.isEmpty()){
                    TreeNode p = s1.pop();
                    aList2.add(p.val);
                    if(p.left != null)
                        s2.add(p.left);
                    if(p.right != null)
                        s2.add(p.right);
                }
                aList.add(aList2);

            }
            else {
                ArrayList<Integer> aList2 = new ArrayList<Integer>();
                while(!s2.isEmpty()){

                    TreeNode p = s2.pop();
                    if(p.right != null)
                        s1.add(p.right);
                    if(p.left != null)
                        s1.add(p.left);
                    aList2.add(p.val);

                }
                aList.add(aList2);
            }
        }
        return aList;
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