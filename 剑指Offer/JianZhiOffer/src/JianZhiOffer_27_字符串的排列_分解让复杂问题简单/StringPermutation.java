package JianZhiOffer_27_字符串的排列_分解让复杂问题简单;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author: sunnyandgood
 * @Date: 2019/7/25 22:23
 * 27、字符串的排列
 * 题目描述：
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */
public class StringPermutation {
//    public ArrayList<String> Permutation(String str) {
//        ArrayList<String> list = new ArrayList<>();
//        if (str == null || str.length() == 0){
//            return list;
//        }
//        if (str.length() == 1){
//            list.add(str);
//            return list;
//        }
//        char[] arr = str.toCharArray();
//        permutation(list,arr,0);
//        Set<String> set = new HashSet<>(list);
//        return new ArrayList<>(set);
//    }
//
//    private void permutation(ArrayList<String> list,char[] arr,int begin){
//        if (begin == arr.length){
//            list.add(Arrays.toString(arr));
//        }else {
//            for (int i = begin;i < arr.length;i++){
//                char temp = arr[i];
//                arr[i] = arr[begin];
//                arr[begin] = temp;
//                permutation(list,arr,begin + 1);
//                temp = arr[i];
//                arr[i] = arr[begin];
//                arr[begin] = temp;
//            }
//        }
//    }

    // 使用 TreeSet 进行去重排序
    private Set<String> permutations = new TreeSet<>();
    public ArrayList<String> Permutation(String str) {
        StringBuilder res = new StringBuilder(str);
        for (int i = 0; i < res.length(); i++) {
            permutation(res, 0, i);
        }
        return new ArrayList<>(permutations);
    }

    /**
     * 把字符串分为两部分，一部分是字符串的第一个字符，剩下的字符串为后一部分
     * 每次拿第一个字符与后部分字符串中的任一个字符进行交换，然后固定第一部分
     * 剩下部分任然分为两部分，重复以上操作
     *
     * @param res
     * @param beginIndex
     * @param replaceIndex
     */
    private void permutation(StringBuilder res, int beginIndex, int replaceIndex) {
        permutations.add(res.toString());
        for (int i = beginIndex + 1; i < res.length(); i++) {
            // 递归
            switchLetter(res, beginIndex, replaceIndex);
            permutation(res, beginIndex + 1, i);
            // 回溯
            switchLetter(res, beginIndex, replaceIndex);
        }
    }

    private void switchLetter(StringBuilder res, int indexA, int indexB) {
        char letterA = res.charAt(indexA);
        res.setCharAt(indexA, res.charAt(indexB));
        res.setCharAt(indexB, letterA);
    }

    public static void main(String[] args) {
        StringPermutation permutation = new StringPermutation();
        System.out.println(permutation.Permutation(""));
        System.out.println(permutation.Permutation("a"));
        System.out.println(permutation.Permutation("abc"));
        System.out.println(permutation.Permutation("aa"));
    }
}
