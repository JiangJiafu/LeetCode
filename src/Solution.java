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
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            set.add(t.charAt(i));
        }
        int index = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0, minRight = 0;
        LinkedList<Integer> slectedChars = new LinkedList<>();
        Map<Character, Integer> count = new HashMap<>();
        while (index < t.length()) {
            char c = s.charAt(index);
            if (!set.contains(c)) {
                index++;
                continue;
            }
            if (count.containsKey(c)) {
                count.put(c, count.get(c) + 1);
                index++;
                continue;
            }
            count.put(c, 1);
            slectedChars.add(index);
            // 判断目前选择的字符是否包含t中所有字符
            if (count.size() == set.size()) {
                while (count.size() == set.size()) {
                    // 保存当前的最优选择
                    int slectedLen = slectedChars.getLast() - slectedChars.getFirst() + 1;
                    if (slectedLen < minLen) {
                        minLen = slectedLen;
                        minLeft = slectedChars.getFirst();
                        minRight = slectedChars.getLast();
                    }
                    // 把当前选择的字符列表中，删除第一个，继续判断
                    int k = slectedChars.removeFirst();
                    int num = count.get(s.charAt(k));
                    if (num == 1) {
                        count.remove(s.charAt(k));
                    } else {
                        count.put(s.charAt(k), num - 1);
                    }
                }
            }
            index += 1;
        }
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));

    }
}