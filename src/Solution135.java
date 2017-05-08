/**
 * 题目：https://leetcode.com/problems/candy/#/description
 *
 * 每个小孩分一个糖果，如果ratings[i]的值大的，分到的糖果一定要多（至少多一个嘛）
 * 每个小孩至少要分到一个糖果，求最少需要多少糖果。
 *
 * 思路：
 * 遍历两次列表，第一次从前往后遍历，只要后一个ratings[i]比前面大，就增加他所分到的糖果数。
 * 第一次遍历结束后，可以确保，只要ratings[i - 1] < ratings[i]，那么第i个小孩分到的糖果一定比
 * 第i - 1个小孩的多。
 *
 * 第二次从后往前遍历，只要前一个小孩的ratings[i]比后一个小孩的ratings[i+1]大，就相应地增加他
 * 的糖果数。但如果此时前一个小孩的糖果数已经比后一个小孩的多了（前一轮调整的结果），那么就不用再
 * 调整了。
 * 第二次遍历结束后，可以确保，只要ratings[i] > ratings[i+1]，那么第i个小孩分到的糖果一定比
 * 第i+1个小孩的多。 同时，第二轮遍历的时候，一定不会破坏第一轮的结果。
 *
 * 综上，可以得到最少需要的糖果数。
 *
 */
public class Solution135 {
    public int candy(int[] ratings) {
        // 最小需要的糖果数
        int result = ratings.length; // 每个人先分一块糖
        // results[i]表示第i个小孩分到的糖果数
        int[] results = new int[ratings.length];
        for (int i = 0; i < results.length; ++i) {
            results[i] = 1;
        }

        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                result += results[i - 1];
                results[i] = 1 + results[i - 1]; // 比前一个多一个糖果即可
            }
        }

        for (int i = ratings.length - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1] && results[i] <= results[i + 1]) {
                result += results[i + 1] + 1 - results[i];
                results[i] = results[i + 1] + 1;
            }
        }
        return result;
    }
}