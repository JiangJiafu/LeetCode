import java.util.Stack;

/**
 * 原题：https://leetcode.com/problems/maximal-rectangle/
 *
 * 思路：使用84题的结论来计算85题的答案。
 *       将85题中的图，一层一层地构造出84题中的矩阵，调用84题的计算方法，算出最大
 *       矩形面积，最终的答案就是85题的答案。
 */
public class Solution85 {
    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) {
            return 0;
        }
        // 将char 转成int 矩阵，便于计算。
        int [][] nmatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            char[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                if (x[j] == '0') {
                    nmatrix[i][j] = 0;
                } else {
                    nmatrix[i][j] = 1;
                }
            }
        }
        // 一层一层地构造一维数组，数组中的元素，表示高度（见84题）
        int[] height = new int[nmatrix[0].length];
        int maxRectangle = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < nmatrix[0].length; j++) {
                // 如果本层的值为0，那么表示断开了，height归0
                if (nmatrix[i][j] == 0) {
                    height[j] = 0;
                } else {
                    // 否则高度叠加。
                    height[j] += nmatrix[i][j];
                }
            }
            // 计算height数组中的最大矩形面积
            int max = largestRectangleArea(height);
            if (max > maxRectangle) {
                maxRectangle = max;
            }
        }
        return maxRectangle;
    }

    /**
     * 这部分是84题的解。输入一个数组，返回数组中最大的矩形面积。
     * @param height
     * @return
     */
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

    public static void main(String[] args) {
        int [] a = {6, 2 ,5, 4, 5, 4,1,6};
        Solution85 s = new Solution85();
        System.out.println(s.largestRectangleArea(a));
    }
}