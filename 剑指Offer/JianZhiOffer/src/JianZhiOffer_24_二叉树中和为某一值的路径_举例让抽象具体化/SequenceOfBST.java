package JianZhiOffer_24_二叉树中和为某一值的路径_举例让抽象具体化;

import java.util.ArrayList;

/**
 * @Author: sunnyandgood
 * @Date: 2019/7/17 22:12
 * 24、二叉树中和为某一值的路径
 * 题目描述：
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 *
 * 解题思路:
 * 当用前序遍历的方式访问到某一结点时，我们把该结点添加到路径上，并累加该结点的值。如果该结点为叶结点并且路径中结点值的和刚好等于输入的整数，
 * 则当前的路径符合要求，我们把它打印出来。如果当前结点不是叶结点，则继续访问它的子结点。当前结点访问结束后，递归函数将自动回到它的父结点。
 * 因此我们在函数退出之前要在路径上删除当前结点并减去当前结点的值，以确保返回父结点时路径刚好是从根结点到父结点的路径。
 * 我们不难看出保存路径的数据结构实际上是一个栈，因为路径要与递归调用状态一致，而递归调用的本质就是一个压栈和出栈的过程。
 */
public class SequenceOfBST {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> resList = new ArrayList<>();
        if(root == null){
            return resList;
        }
        ArrayList<Integer> stack = new ArrayList<>();
        int sum = 0;
        path(root,target,resList,stack,sum);
        return resList;
    }
    public void path(TreeNode root,int target,ArrayList<ArrayList<Integer>> arr, ArrayList<Integer> stack,int sum){
        if(root==null)
            return ;
        sum+=root.val;
        if(root.left==null&&root.right==null){
            if(sum==target) {
                stack.add(root.val);
                arr.add(new ArrayList<>(stack));
                stack.remove(stack.size()-1);
            }
            return;
        }
        stack.add(root.val);
        path(root.left,target,arr,stack,sum);
        path(root.right,target,arr,stack,sum);
        stack.remove(stack.size()-1);
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