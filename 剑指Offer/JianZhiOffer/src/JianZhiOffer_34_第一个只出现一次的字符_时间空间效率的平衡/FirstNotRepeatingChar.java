package JianZhiOffer_34_第一个只出现一次的字符_时间空间效率的平衡;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sunnyandgood
 * @Date: 2019/8/6 21:58
 * 33、第一个只出现一次的字符
 * 题目描述
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
 * 如果没有则返回 -1（需要区分大小写）.
 */
public class FirstNotRepeatingChar {
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0){
            return -1;
        }
        Map<Character,Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (Character ch : chars){
            map.put(ch,map.getOrDefault(ch,0) + 1);
        }
        for (int i = 0;i < chars.length;i++){
            if (map.get(chars[i]) == 1){
                return i;
            }
        }
        return -1;
    }

    public int FirstNotRepeatingChar_1(String str) {
        if (str == null || str.length() == 0){
            return -1;
        }
        int[] direction = new int[256];
        char[] chars = str.toCharArray();
        for (char ch : chars){
            direction[ch]++;
        }
        for (int i = 0;i < chars.length;i++){
            if (direction[chars[i]] == 1){
                return i;
            }
        }
        return -1;
    }

    public int FirstNotRepeatingChar_2(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (str.indexOf(chars[i]) == str.lastIndexOf(chars[i])) {
                return i;
            }
        }
        return -1;
    }
}
