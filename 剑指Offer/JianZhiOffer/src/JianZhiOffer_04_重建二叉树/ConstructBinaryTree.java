package JianZhiOffer_04_重建二叉树;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/16 11:42
 * 4、重建二叉树
 * 题目描述：
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * 解题思路：
 * 前序遍历序列的第一个数字1就是根结点的值。
 * 扫描中序遍历序列，就能确定根结点的值的位置。
 * 根据中序遍历特点，在根结点的值1前面的3个数字都是左子树结点的值，位于1后面的数字都是右子树结点的值。
 * 由于在中序遍历序列中，有3个数字是左子树结点的值，因此左子树总共有3个左子结点。
 * 同样，在前序遍历的序列中，根结点后面的3个数字就是3个左子树结点的值，
 * 再后面的所有数字都是右子树结点的值。这样我们就在前序遍历和中序遍历两个序列中，分别找到了左右子树对应的子序列。
 * 既然我们已经分别找到了左、右子树的前序遍历序列和中序遍历序列，我们可以用同样的方法分别去构建左右子树。
 * 也就是说，接下来的事情可以用递归的方法去完成。
 */
public class ConstructBinaryTree {
    /**
     * 先根据前序遍历序列的第一个数字创建根结点，接下来在中序遍历序列中找到根结点的位置，
     * 这样就能确定左、右子树结点的数量。在前序遍历和中序遍历的序列中划分了左、右子树结点的值之后，
     * 我们就可以递归地调用函数，去分别构建它的左右子树。
     */
    public TreeNode constructCore(int[] pre,int preStart,int preEnd,int[] mid,int midStart,int midEnd){
        if (preStart > preEnd || midStart > midEnd){
            return null;
        }
        //前序遍历的第一个值是根节点的值
        TreeNode root = new TreeNode(pre[preStart]);
        root.left = null;
        root.right = null;

        if (preStart == preEnd){
            if (midStart == midEnd && preStart == midStart){
                return root;
            }
        }

        //在中序遍历中找到根节点的值
        int rootIndex = midStart;
        while (rootIndex <= midEnd && mid[rootIndex] != root.val){
            ++rootIndex;
        }

        int leftLength = rootIndex - midStart;
        int leftPreEnd = preStart + leftLength;

        if (leftLength > 0){
            //构建左子树
            root.left = constructCore(pre,preStart+1,leftPreEnd,mid,midStart,rootIndex-1);
        }
        if (leftLength < preEnd - preStart){
            //构建右子树
            root.right = constructCore(pre,leftPreEnd+1,preEnd,mid,rootIndex+1,midEnd);
        }

        return root;
    }

    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        if (pre == null || in == null){
            return null;
        }
        TreeNode root = constructCore(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTree tree = new ConstructBinaryTree();
        System.out.println(tree.reConstructBinaryTree(new int[]{1,2,4,7,3,5,6,8},new int[]{4,7,2,1,5,3,8,6}));
    }
}
