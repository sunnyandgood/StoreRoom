package leet_code_0139_单词拆分;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: sunnyandgood
 * @Date: 2019/6/2 12:29
 * 139. 单词拆分
 */
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] memory = new boolean[length+1];
        memory[0] = true;
        for (int i=0;i<=length;i++){
            for (int j=0;j<length;j++){
                if (memory[j] && wordDict.contains(s.substring(j,i))){
                    memory[i] = true;
                    break;
                }
            }
        }
        return memory[length];
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(solution.wordBreak("thisisadog",Arrays.asList("this","thisis","is","a","dog")));
    }
}
/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 *     拆分时可以重复使用字典中的单词。
 *     你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */