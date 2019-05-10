package leet_code_00003;

/**
 * @Author: sunnyandgood
 * @Date: 2019/5/11 1:35
 * 无重复字符的最长子串
 */
public class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 滑动窗口解法
        // freq 用于存储滑动窗口中某个字符出现的次数
        int freq[] = new int[256];
        // 滑动窗口为 s[l, r]
        int l = 0;
        // r = -1，表示窗口初始大小为 0
        int r = -1;
        int len = 0;
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                // 右边界的下一个元素未在窗口中出现过，就加入到窗口中
                r++;
                freq[s.charAt(r)]++;
            } else {
                // 否则，就将窗口右移动，直到把重复元素移出去为止
                freq[s.charAt(l)]--;
                l++;
            }
            len = r - l + 1 > len ? r - l + 1 : len;
        }
        return len;
    }
    public static void main(String[] args) {
        String str = "abcabcbb";
        int num = lengthOfLongestSubstring(str);
        System.out.println(num);
    }
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     */
}
