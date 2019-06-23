package JianZhiOffer_17_树的子结构_代码的鲁棒性;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/23 9:28
 * 17、树的子结构
 * 题目描述：
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * 解题思路：
 *      8               8
 *    /   \            / \
 *   8     7          9   2
 *  / \
 * 9   2
 *    / \
 *   4   7
 * 要查找树A中是否存在和树B结构一样的子树，我们可以分成两步：
 * 第一步在树A中找到和B的根结点的值一样的结点R，第二步再判断树A中以R为根结点的子树是不是包含和树B 一样的结构。
 * 以上面的两棵树为例来详细分析这个过程。首先我们试着在树A中找到值为8（树B的根结点的值）的结点。
 * 从树A的根结点开始遍历，我们发现它的根结点的值就是8。接着我们就去判断树A的根结点下面的子树是不是含有和树B一样的结构。
 * 在树A中，根结点的左子结点的值是8，而树B的根结点的左子结点是9，对应的两个结点不同。
 * 因此我们仍然需要遍历树A，接着查找值为8的结点。我们在树的第二层中找到了一个值为8的结点，然后进行第二步判断，
 * 即判断这个结点下面的子树是否含有和树B一样结构的子树。于是我们遍历这个结点下面的子树，先后得到两个子结点9和2，
 * 这和树B的结构完全相同。此时我们在树A中找到了一个和树B的结构一样的子树，因此树B是树A的子结构。
 *
 * 第一步在树A中查找与根结点的值一样的结点，这实际上就是树的遍历。对二叉树这种数据结构熟悉的读者自然知道可以用递归的方法去遍历，
 * 也可以用循环的方法去遍历。由于递归的代码实现比较简洁，面试时如果没有特别要求，我们通常都会采用递归的方式。
 */
public class SubstructureInTree {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
         boolean result = false;
         if (root1 != null && root2 != null){
             if (root1.val == root2.val){
                result = doesTree1HaveTree2(root1,root2);
             }
             if (!result){
                 result = HasSubtree(root1.left,root2);
             }
             if (!result){
                 result = HasSubtree(root1.right,root2);
             }
         }
         return result;
    }

    boolean doesTree1HaveTree2(TreeNode tree1,TreeNode tree2){
        if (tree2 == null){
            return true;
        }
        if (tree1 == null){
            return false;
        }
        if (tree1.val != tree2.val){
            return false;
        }
        return (doesTree1HaveTree2(tree1.left,tree2.left) && doesTree1HaveTree2(tree1.right,tree2.right));
    }
    public static void main(String[] args) {

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