/**
 * 原题：https://leetcode.com/problems/distinct-subsequences/
 * 
 * 解题思路：动态规则
 * 令dp[i][j]表示s[1,i],t[1,j]的解，假设字符串的下标从1开始，s[0],t[0]表示空字符串。我们有
 * dp[i][j] = dp[i-1][j-1] + dp[i-1][j] 如果 s[i] == t[j] i > 0, j>0
 * dp[i][j] = dp[i-1][j]                如果s[i] != t[j]  i > 0
 * dp[i][j] = 1                         如果 j = 0
 * dp[i][j] = 0                         如果j > i
 * 
 * 
 * @author JiangJiafu
 *
 */
public class Solution115 {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        // 初始化动态规划表
        for (int i = 0; i <= s.length(); ++i) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 1; j <= i && j <=t.length(); ++j) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[s.length()][t.length()];
    }
}