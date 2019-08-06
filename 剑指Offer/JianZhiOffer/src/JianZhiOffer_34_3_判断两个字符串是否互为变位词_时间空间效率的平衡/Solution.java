package JianZhiOffer_34_3_判断两个字符串是否互为变位词_时间空间效率的平衡;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/6 23:21
 * 34.3、判断两个字符串是否互为变位词
 * eg: silent -> listen ；evil -> live
 */
public class Solution {
    public boolean sollution(String str1, String str2){
        if (str1 == null || str1.length() == 0 || str2 == null ||str2.length() == 0){
            return true;
        }
        int[] direction = new int[256];
        for (int i = 0;i < str1.length();i++){
            direction[str1.charAt(i)] += 1;
        }
        for (int i = 0;i < str2.length();i++){
            direction[str1.charAt(i)] -= 1;
        }
        boolean flag = true;
        for (int val : direction){
            if (val != 0){
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.sollution("silent","listen"));
        System.out.println(solution.sollution("evil","live"));
        System.out.println(solution.sollution("silent","q"));
    }
}
