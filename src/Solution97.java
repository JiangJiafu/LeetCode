import java.util.HashMap;
import java.util.Map;

/**
 * 原题：https://leetcode.com/problems/interleaving-string/
 *
 * 思路：
 * 使用动态规则，
 * 令ans(i, j)表示s1[0:i]（s1的前i个字符，下同），s2[0:j]是否和s3[0:i+j]是
 * interleaving的关系，ans(i,j) ==1表示是，==0表示否。
 * 那么，我们有递推式：
 * ans[i,j] = 1 if ans[i][j-1] == 1 && s2[j-1]==s3[i+j-1] or ans[i-1][j] == 1 && s1[i-1]==s3[i+j-1]
 * ans[i,j] = 0 其它情况
 *
 * ans[s1.length,s2.length]即为答案
 *
 */
public class Solution97 {

    public boolean checkCharNumber(String s1, String s2, String s3) {
        Map<Character, Integer> set1 = new HashMap<>();
        Map<Character, Integer> set2 = new HashMap<>();

        for (char c : s1.toCharArray()) {
            Integer n = set1.get(c);
            if (n == null) {
                set1.put(c, 1);
            } else {
                set1.put(c, n + 1);
            }
        }

        for (char c : s2.toCharArray()) {
            Integer n = set1.get(c);
            if (n == null) {
                set1.put(c, 1);
            } else {
                set1.put(c, n + 1);
            }
        }

        for (char c : s3.toCharArray()) {
            Integer n = set2.get(c);
            if (n == null) {
                set2.put(c, 1);
            } else {
                set2.put(c, n + 1);
            }
        }

        if (set1.keySet().size() != set2.keySet().size()) {
            return false;
        }
        for (Character c : set1.keySet()) {
            int numberOfC = set1.get(c);
            Integer numberOfC2 = set2.get(c);
            if (numberOfC2 == null) {
                return false;
            }
            if (numberOfC != numberOfC2.intValue()) {
                return false;
            }
        }
        return true;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        // 判断字符数是否一致
        if (!checkCharNumber(s1, s2, s3)) {
            return false;
        }

        // 使用动态规则
        int[][] ans = new int[s1.length() + 1][s2.length() + 1];
        ans[0][0] = 1;
        for (int i = 1; i <= s2.length(); i++) {
            if (s2.charAt(i - 1) == s3.charAt(i - 1)) {
                ans[0][i] = 1;
            } else {
                break;
            }
        }
        for (int i = 1; i <= s1.length(); i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1)) {
                ans[i][0] = 1;
            } else {
                break;
            }
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                // 求ans[i][j]
                // 如果ans[i][j-1]为真，且s2[j-1]=s3[i+j-1]
                if (ans[i][j-1] == 1 && s2.charAt(j-1) == s3.charAt(i+j-1)) {
                    ans[i][j] = 1;
                } else if (ans[i-1][j] == 1 && s1.charAt(i-1) == s3.charAt(i+j-1)) {
                    ans[i][j] = 1;
                } else {
                    ans[i][j] = 0;
                }
            }
        }
        return ans[s1.length()][s2.length()] == 1;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        System.out.println(solution.isInterleave(s1, s2, s3));
    }
}