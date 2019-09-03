package JianZhiOffer_54_字符流中第一个不重复的字符_字符串;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: sunnyandgood
 * @Date: 2019/9/3 23:44
 * 54、字符流中第一个不重复的字符
 * 题目描述：
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当
 * 从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 输出描述:
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class FirstAppearingOnce {
    List<Character> list = new ArrayList<>();
    Set<Character> set = new HashSet<>();
    //Insert one char from stringstream
    public void Insert(char ch) {
        list.add(ch);
        if (set.contains(ch)) {
            set.remove(ch);
        } else {
            set.add(ch);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        if (!set.isEmpty()) {
            for (Character ch : list) {
                if (set.contains(ch)) {
                    return ch;
                }
            }
        }
        return '#';
    }
}
