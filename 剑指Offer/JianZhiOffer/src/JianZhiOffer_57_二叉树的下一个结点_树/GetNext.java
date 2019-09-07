package JianZhiOffer_57_二叉树的下一个结点_树;

/**
 * @Author: sunnyandgood
 * @Date: 2019/9/7 23:49
 * 57、二叉树的下一个结点
 * 题目描述：
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class GetNext {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {

    }
}
class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}