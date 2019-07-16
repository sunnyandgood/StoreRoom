package JianZhiOffer_23_二叉搜索树的后序遍历序列_举例让抽象具体化;

/**
 * @Author: sunnyandgood
 * @Date: 2019/7/16 22:41
 * 23、二叉搜索树的后序遍历序列
 * 题目描述：
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * 解题思路:
 * 在后序遍历得到的序列中，最后一个数字是树的根结点的值。数组中前面的数字可以分为两部分：
 * 第一部分是左子树结点的值，它们都比根结点的值小；第二部分是右子树结点的值，它们都比根结点的值大。
 *
 * 以数组{5,7,6,9,11,10,8}为例，后序遍历结果的最后一个数字8就是根结点的值。在这个数组中，前3个数字5、7和6都比8小，
 * 是值为8的结点的左子树结点；后3个数字9、11和10都比8大，是值为8的结点的右子树结点。
 * 我们接下来用同样的方法确定与数组每一部分对应的子树的结构。这其实就是一个递归的过程。对于序列5、7、6，
 * 最后一个数字6是左子树的根结点的值。数字5比6小，是值为6的结点的左子结点，而7则是它的右子结点。同样，在序列9、11、10中，
 * 最后一个数字10是右子树的根结点，数字9比10小，是值为10的结点的左子结点，而11则是它的右子结点。
 */
public class PrintBSTFromTopToBottom {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length == 0){
            return false;
        }
        boolean res = IsTreeBST(sequence, 0, sequence.length - 1);
        return res;
    }

    private boolean IsTreeBST(int [] sequence,int start,int end ){
        if(end <= start)
            return true;
        int i = start;
        for (; i < end; i++) {
            if(sequence[i] > sequence[end])
                break;
        }
        for (int j = i; j < end; j++) {
            if(sequence[j] < sequence[end])
                return false;
        }
        return IsTreeBST(sequence, start, i-1) && IsTreeBST(sequence, i, end-1);
    }
}
