package 剑指offer.图;

import java.util.Arrays;
import java.util.List;

public class No69_顺时针打印矩阵 {
    public static void main(String[] args) {
        System.out.println("spiralArray() = " + Arrays.toString(spiralArray(new int[][]{{2, 5}, {8, 4}, {0, -1}})));
//        System.out.println("spiralArray() = " + Arrays.toString(spiralArray(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}})));

    }

    public static int[] spiralArray(int[][] array) {
        int m = array.length;
        if (m == 0) {
            return new int[]{};
        }
        int n = array[0].length;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int cursor = 0;
        int[] res = new int[m * n];
        //定义角点
        int ltPointX = 0;
        int ltPointY = 0;
        int rbPointX = m - 1;
        int rbPointY = n - 1;
        while (ltPointX <= rbPointX && ltPointY <= rbPointY) {
            //每次从角点开始搜索
            int i = ltPointX;
            int j = ltPointY;
            for (int[] direction : directions) {
                while (i >= ltPointX && i <= rbPointX && j >= ltPointY && j <= rbPointY && cursor < m * n) {
                    res[cursor++] = array[i][j];
                    i = i + direction[0];
                    j = j + direction[1];
                }
                i = i - direction[0];
                j = j - direction[1];

                cursor--;
                //如果到达数组尽头，那么直接跳出，不去除res末尾的重复值
                if (cursor == m * n - 1) {
                    break;
                } else {
                    res[cursor] = 0; //否则，去除res末尾的重复值
                }
            }


            //变动角点
            ltPointX++;
            ltPointY++;
            rbPointX--;
            rbPointY--;
        }
        return res;
    }

    //k神的解答，优雅。https://leetcode.cn/problems/spiral-matrix-ii/solutions/12594/spiral-matrix-ii-mo-ni-fa-she-ding-bian-jie-qing-x/
    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){
            for(int i = l; i <= r; i++) mat[t][i] = num++; // left to right.
            t++;
            for(int i = t; i <= b; i++) mat[i][r] = num++; // top to bottom.
            r--;
            for(int i = r; i >= l; i--) mat[b][i] = num++; // right to left.
            b--;
            for(int i = b; i >= t; i--) mat[i][l] = num++; // bottom to top.
            l++;
        }
        return mat;
    }

}
