package 剑指offer.动态规划;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class No64_珠宝的最高价值 {
    private static int curMax = 0;

    public static void main(String[] args) {
        System.out.println("args = " + jewelleryValue(new int[][]{{1, 2}, {1, 1}}));
    }

    //暴力法 （容易超时）
    public static int jewelleryValue(int[][] frame) {
        search(frame, 0, 0, new ArrayList<>());
        return curMax;
    }

    public static void search(int[][] frame, int i, int j, List<Integer> res) {
        int row = frame.length;
        int col = frame[0].length;
        //将节点加入
        res.add(frame[i][j]);
        //1.终止条件
        if (i == row - 1 && j == col - 1) {
            int sum = res.stream().mapToInt(Integer::intValue).sum();
            if (sum > curMax) {
                curMax = sum;
            }
            return;
        }
        //向右遍历
        if (j + 1 < col) {
            search(frame, i, j + 1, res);
            res.remove(res.size() - 1);
        }
        //向下遍历
        if (i + 1 < row) {
            search(frame, i + 1, j, res);
            res.remove(res.size() - 1);
        }
    }

    //2. 尝试使用动态规划 （f(i,j) 等于 f(i,j−1) 和 f(i−1,j) 中的较大值加上当前单元格珠宝价值 frame(i,j) 。）
    public static int jewelleryValue2(int[][] frame) {
        int row = frame.length;
        int col = frame[0].length;
        //没必要新建新的二维dp数组，可以直接将frame当作dp数组，dp(i,j) 代表从棋盘的左上角开始，到达单元格 (i,j) 时能拿到珠宝的最大累计价值
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    continue; //保持原样不动
                }
                if (i == 0 && j != 0) { //向右移动
                    frame[i][j] = frame[i][j] + frame[i][j - 1];
                }
                if (i != 0 && j == 0) { //向下移动
                    frame[i][j] = frame[i][j] + frame[i - 1][j];
                }
                if (i != 0 && j != 0) {
                    frame[i][j] = frame[i][j] + Math.max(frame[i][j - 1], frame[i - 1][j]);
                }
            }
        }
        return frame[row - 1][col - 1];
    }

    //3.进一步优化,可以优化判断逻辑，此外，当 frame 矩阵很大时，i=0 或 j=0 的情况仅占极少数，相当循环每轮都冗余了一次判断。因此，可先初始化矩阵第一行和第一列，再开始遍历递推
    public static int jewelleryValue3(int[][] frame) {
        int row = frame.length;
        int col = frame[0].length;
        //没必要新建新的二维dp数组，可以直接将frame当作dp数组，dp(i,j) 代表从棋盘的左上角开始，到达单元格 (i,j) 时能拿到珠宝的最大累计价值
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    continue; //保持原样不动
                } else if (i == 0) { //向右移动
                    frame[i][j] = frame[i][j] + frame[i][j - 1];
                } else if (j == 0) { //向下移动
                    frame[i][j] = frame[i][j] + frame[i - 1][j];
                } else {
                    frame[i][j] = frame[i][j] + Math.max(frame[i][j - 1], frame[i - 1][j]);
                }
            }
        }
        return frame[row - 1][col - 1];
    }
}
