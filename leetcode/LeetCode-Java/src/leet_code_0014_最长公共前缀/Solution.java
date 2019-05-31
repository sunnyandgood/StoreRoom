package leet_code_0014_最长公共前缀;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/31 23:22
 * 14. 最长公共前缀
 */
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs==null || strs.length==0){
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (int i=0;i<strs.length;i++){
            int length = strs[i].length();
            minLength = length < minLength ? length : minLength;
        }

        StringBuffer buffer = new StringBuffer("");
        boolean flag = false;
        for (int i=0;i<minLength;i++){
            char ch = strs[0].charAt(i);
            buffer.append(ch);
            for (int j=1;j<strs.length;j++){
                char c = strs[j].charAt(i);
                if (c!=ch){
                    flag = true;
                    buffer.deleteCharAt(i);
                    break;
                }
            }
            if (flag){
                break;
            }
        }

        return buffer.toString();
    }

    public String longestCommonPrefix1(String[] strs) {
        if (strs==null || strs.length==0){
            return "";
        }
        for (int i=0;i<strs[0].length();i++){
            char ch = strs[0].charAt(i);
            for (int j=1;j<strs.length;j++){
                if (strs[j].length()<=i || strs[j].charAt(i)!=ch){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs1 = {"flower","flow","flight"};
        String[] strs2 = {"dog","racecar","car"};
        String[] strs3 = {};
        String[] strs4 = {"aa","a"};
        System.out.println(solution.longestCommonPrefix(strs3));
        System.out.println(solution.longestCommonPrefix(strs1));
        System.out.println(solution.longestCommonPrefix(strs2));
        System.out.println(solution.longestCommonPrefix(strs4));
    }
}
/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 */