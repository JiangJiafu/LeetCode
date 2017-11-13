import java.util.Stack;

/**
 * 原题：https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * 思路：http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 */
public class Solution84 {
    public int largestRectangleArea(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args[]) {
        int [] a = {6, 2 ,5, 4, 5, 4,1,6};
        Solution84 s = new Solution84();
        System.out.println(s.largestRectangleArea(a));
    }
}