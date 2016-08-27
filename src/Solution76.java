import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 题目：https://leetcode.com/problems/minimum-window-substring/
 * 思路：
 * 1. 统计目标字符串中每个字符出现的次数，使用hash保存
 * 2. 遍历原字符串，遍历的过程中，统计出现在目标字符串中的字符的个数，并将该字符入队。
 * 3. 一旦发现队列中的字符数目已经覆盖目标字符串中的所有字符，对比并记录结果。
 * 4. 不断从队列头部取出字符，并再次判断队列中剩余的字符是否可以覆盖目标字符。
 *    即不断缩小范围，直到队列中剩余的字符已经不能覆盖目标字符为止，然后这时继续遍历源
 *    字符串（即继续增加字符）
 *
 * @author JiangJiafu
 */
public class Solution76 {
    public String minWindow(String s, String t) {
        // targetCount中记录目标字符串中每个字符出现的个数
        Map<Character, Integer> targetCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (targetCount.containsKey(c)) {
                targetCount.put(c, targetCount.get(c) + 1);
            } else {
                targetCount.put(c, 1);
            }
        }
        int index = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0, minRight = 0;
        // 记录当前已经选择的字符
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
            if (count.get(c) .equals(targetCount.get(c))) {
                okNum++;
            }
            // 判断目前选择的字符是否包含t中所有字符
            while (okNum == targetCount.size()) {
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
                if (count.get(fc).equals(targetCount.get(fc) - 1)) {
                    okNum--;
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
        Solution76 solution = new Solution76();

    }
}