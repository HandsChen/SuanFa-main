package 剑指offer.图;

import java.util.*;

public class No67_矩阵中的路径 {

    public static void main(String[] args) {
//        System.out.println("wordPuzzle() = " + wordPuzzle(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "SEE"));
//        System.out.println("wordPuzzle() = " + wordPuzzle(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCESEEEFS"));
        System.out.println("wordPuzzle() = " + wordPuzzle2(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCESEEEFS"));
//        System.out.println("wordPuzzle() = " + wordPuzzle(new char[][]{{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}}, "AAB"));

    }

    //自己想的暴力法，花了好长时间，正式面试时候肯定不可以这样
    public static boolean wordPuzzle(char[][] grid, String target) {
        //首先在grid中寻找启始字母
        char startChar = target.charAt(0);
        Deque<int[]> startCharPos = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == startChar) {
                    startCharPos.offerLast(new int[]{i, j});
                }
            }
        }
        //创建used map
        //然后在启始字母周围寻找下一个字母
        while (!startCharPos.isEmpty()) {  //优化点一：这里完全没有必要将position放在集合里，完全可以边执行上面的双循环，边search
            //使用新的启始字符的位置
            int[] pos = startCharPos.pollFirst();
            if (search(grid, pos[0], pos[1], 1, target, new boolean[grid.length][grid[0].length])) { //优化点二：used数组没有必要每次都新创建一个，其实可以复用
                return true;
            }
        }
        return false;
    }

    public static boolean search(char[][] grid, int i, int j, int cursor, String target, boolean[][] usedMap) {
        if (cursor >= target.length()) {
            return true;
        }
        usedMap[i][j] = true;
        char nextChar = target.charAt(cursor);
        boolean bot = false, top = false, right = false, left = false;
        if (i + 1 < grid.length && !usedMap[i + 1][j] && grid[i + 1][j] == nextChar) { //向下嗅探
            usedMap[i][j] = true;
            bot = search(grid, i + 1, j, cursor + 1, target, usedMap);
            usedMap[i][j] = bot;
        }
        if (i - 1 >= 0 && !usedMap[i - 1][j] && grid[i - 1][j] == nextChar) { //向上嗅探
            usedMap[i][j] = true;
            top = search(grid, i - 1, j, cursor + 1, target, usedMap);
            usedMap[i][j] = top;
        }
        if (j + 1 < grid[0].length && !usedMap[i][j + 1] && grid[i][j + 1] == nextChar) { //向右嗅探
            usedMap[i][j] = true;
            right = search(grid, i, j + 1, cursor + 1, target, usedMap);
            usedMap[i][j] = right;
        }
        if (j - 1 >= 0 && !usedMap[i][j - 1] && grid[i][j - 1] == nextChar) { //向左嗅探
            usedMap[i][j] = true;
            left = search(grid, i, j - 1, cursor + 1, target, usedMap);
            usedMap[i][j] = left;
        }
        if (bot || top || right || left) { //如果发现了匹配元素
            return true;
        } else { //如果在i,j周围没有发现下一个匹配元素，那么就将i,j位置处的usedMap标记清空
            usedMap[i][j] = false;
            return false;
        }
    }

    public static boolean wordPuzzle2(char[][] grid, String target) {
        if (grid == null || grid.length == 0 || target == null || target.isEmpty()) {
            return false;
        }

        char startChar = target.charAt(0);
        boolean[][] used = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == startChar && dfs(grid, i, j, target, 0, used)) {
                    return true;
                }
            }
        }
        return false;
    }

    //深度搜索
    private static boolean dfs(char[][] grid, int i, int j, String target, int index, boolean[][] used) {
        if (index == target.length() - 1) { //搜到目标字符串末尾，就返回true
            return true;
        }

        used[i][j] = true; //一进来就将其在used数组中标记

        // 方向数组：下、上、右、左
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; //为了便于运算，定义一个方向数组

        for (int[] dir : directions) {
            //获取i,j周围下一个目标坐标
            int newI = i + dir[0];
            int newJ = j + dir[1];

            if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[0].length && !used[newI][newJ] && grid[newI][newJ] == target.charAt(index + 1)) {
                //走到这里说明i，j附近有与下一个char(即target.charAt(index + 1))相等的值
                if (dfs(grid, newI, newJ, target, index + 1, used)) { //那么就以新值坐标为锚点，执行下一次搜索
                    return true;
                }
            }
        }
        //走到这里说明，从i,j出发无法找到target
        used[i][j] = false; //那么就将其used数组标志位重置
        return false; //最后返回失败结果
    }
}
