import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * 题目：https://leetcode.com/problems/minimum-window-substring/
 * @author JiangJiafu
 *
 */
public class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> set = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (set.containsKey(c)) {
                set.put(c, set.get(c) + 1);
            } else {
                set.put(c, 1);
            }
        }
        int index = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0, minRight = 0;
        LinkedList<Integer> slectedChars = new LinkedList<>();
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            count.put(c, 0);
        }
        int okNum = 0; // count中已经满足匹配个数的字符数
        while (index < s.length()) {
            char c = s.charAt(index);
            if (!count.containsKey(c)) {
                index++;
                continue;
            }
            count.put(c, count.get(c) + 1);
            slectedChars.add(index);
            if (count.get(c) == set.get(c)) {
                okNum++;
            }
            // 判断目前选择的字符是否包含t中所有字符
            if (okNum == set.size()) {
                while (okNum == set.size()) {
                    // 保存当前的最优选择
                    int slectedLen = slectedChars.getLast() - slectedChars.getFirst() + 1;
                    if (slectedLen < minLen) {
                        minLen = slectedLen;
                        minLeft = slectedChars.getFirst();
                        minRight = slectedChars.getLast();
                    }
                    // 把当前选择的字符列表中，删除第一个，继续判断
                    int k = slectedChars.removeFirst();
                    char fc = s.charAt(k);
                    count.put(fc, count.get(fc) - 1);
                    if (count.get(fc) == set.get(fc) - 1) {
                        okNum--;
                    }
                }
            }
            index++;
        }
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minWindow("bba", "ab"));

    }
}