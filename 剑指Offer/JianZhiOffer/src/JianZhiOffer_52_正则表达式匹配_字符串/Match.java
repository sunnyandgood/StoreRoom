package JianZhiOffer_52_正则表达式匹配_字符串;

/**
 * @Author: sunnyandgood
 * @Date: 2019/9/1 0:36
 * 52、正则表达式匹配
 * 题目描述：
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class Match {
    public boolean match(char[] str, char[] pattern) {
        return matchTwo(str,0,str.length,pattern,0,pattern.length);
    }
    private boolean matchTwo(char[] str, int i, int length1, char[] pattern, int j, int length2) {
        if(i == length1 && j == length2) {
            return true;
        }
        if(i == length1 && j != length2) {
            while(j != length2){
                if(pattern[j] != '*' && (j + 1 >= length2 || pattern[j+1] != '*')){
                    return false;
                }
                j++;
            }
            return true;
        }
        if(i != length1 && j == length2) {
            return false;
        }
        if(j + 1 == length2){
            if(str[i] == pattern[j] || pattern[j] == '.')
                return matchTwo(str, i + 1, length1, pattern, j + 1, length2);
            else {
                return false;
            }
        }
        if((str[i] == pattern[j] || pattern[j] == '.') && pattern[j+1] != '*')
            return matchTwo(str, i + 1, length1, pattern, j + 1, length2);
        if((str[i] == pattern[j] || pattern[j] == '.') && pattern[j+1] == '*')
            return matchTwo(str, i, length1, pattern, j + 2, length2)||matchTwo(str, i + 1, length1, pattern, j, length2);
        if(pattern[j+1] == '*')
            return matchTwo(str, i, length1, pattern, j + 2, length2);
        return false;
    }




    public boolean match1(char[] str, char[] pattern) {
        return match(str, 0, pattern, 0);
    }
    private boolean match(char[] str, int s, char[] pattern, int p) {
        // 模式串匹配到末尾时，判断待匹配串是否匹配完毕
        if (p == pattern.length) {
            return s == str.length;
        }
        // 若模式串的第二个字符为'*'，可先略过，比较之后的字符串
        // 考虑诸如 str=[]，pattern = [.*] 或 str=[bc]，pattern = [a*bc] 等情况
        if (p + 1 < pattern.length
                && pattern[p + 1] == '*'
                && match(str, s, pattern, p + 2)) {
            return true;
        }
        if (s < str.length && (str[s] == pattern[p] || pattern[p] == '.')) {
            // 考虑诸如 str=[abc]，pattern = [.bc] 情况
            if (match(str, s + 1, pattern, p + 1)) {
                return true;
            }
            // 考虑诸如 str=[abc]，pattern = [.*] 或 str=[aaa]，pattern = [a*] 等情况
            if (p + 1 < pattern.length
                    && pattern[p + 1] == '*'
                    && match(str, s + 1, pattern, p)) {
                return true;
            }
        }
        return false;
    }
}
