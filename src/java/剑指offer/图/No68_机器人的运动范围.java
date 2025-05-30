package 剑指offer.图;

import java.util.LinkedList;
import java.util.Queue;

public class No68_机器人的运动范围 {
    public static void main(String[] args) {
        System.out.println("wardrobeFinishing() = " + wardrobeFinishing5(36, 11, 15));

    }

    //自己想的暴力法，会超出时间
    public static int wardrobeFinishing(int m, int n, int cnt) {
        int count = 0;
        boolean[][] map = new boolean[m][n];
        boolean[][] judged = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int iDigit = digit(i);
                int jDigit = digit(j);
                //标记一定不需要整理的格子的位置
                map[i][j] = iDigit + jDigit <= cnt; //标记可能需要整理的格子的位置
            }
        }
        //然后我们判断可能需要整理的格子哪些能够真正到达
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]) {
                    if (j - 1 >= 0 && i - 1 >= 0 && (judged[i][j - 1] || judged[i - 1][j])) { //如果当前格子的上方和左边已经被判定为需要整理，那么作为其相邻元素，必定需要整理，可以通过continue跳过dfs逻辑
                        count++;
                        continue;
                    }
                    if (dfs(map, 0, 0, i, j)) {
                        judged[i][j] = true; //记录搜索结果
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static boolean dfs(boolean[][] map, int startI, int startJ, int targetI, int targetJ) {
        if (startI == targetI && startJ == targetJ) { //如果坐标已经到了目标坐标，那么直接返回true
            return true;
        }
        int[][] directions = new int[][]{{0, 1}, {1, 0}}; //只有向右，和向下移动
        for (int[] direction : directions) {
            int newI = startI + direction[0];
            int newJ = startJ + direction[1];
            if (newI >= 0 && newI < map.length && newJ >= 0 && newJ < map[0].length && map[newI][newJ]) {
                //能走到这里说明新锚点满足条件
                if (dfs(map, newI, newJ, targetI, targetJ)) { //那么就以新锚点为起始点进行下一次搜索
                    return true;
                }
            }
        }
        return false;
    }

    public static int digit(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    //2. 将原来代码进行AI优化时间复杂度的结果
    public static int wardrobeFinishing2(int m, int n, int cnt) {
        int count = 0;
        boolean[][] map = new boolean[m][n];
        boolean[][] visited = new boolean[m][n]; // 标记是否已访问
        boolean[][] reachable = new boolean[m][n]; // 标记是否可达

        // 初始化 map[i][j]，标记满足条件的格子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = (digit(i) + digit(j) <= cnt);
            }
        }

        // 从 (0,0) 开始 DFS，标记所有可达的满足条件的格子
        dfs2(map, visited, reachable, 0, 0);

        // 统计所有可达且满足条件的格子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (reachable[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    //2.
    private static void dfs2(boolean[][] map, boolean[][] visited, boolean[][] reachable, int i, int j) {
        // 越界或已访问或不满足条件的格子直接返回
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || visited[i][j] || !map[i][j]) {
            return;
        }

        // 标记当前格子为已访问且可达
        visited[i][j] = true;
        reachable[i][j] = true;

        // 只向右或向下移动
        dfs2(map, visited, reachable, i, j + 1); // 向右
        dfs2(map, visited, reachable, i + 1, j); // 向下
    }

    //3.借鉴AI的代码，在我原来的代码基础上修改超时问题。
    public static int wardrobeFinishing3(int m, int n, int cnt) {
        int count = 0;
        boolean[][] map = new boolean[m][n];
        boolean[][] reachable = new boolean[m][n];
        boolean[][] visited = new boolean[m][n]; //因为会存在有些格子会被重复搜索到，因此还需要一个visited数组记录其是否已经被搜索过
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int iDigit = digit(i);
                int jDigit = digit(j);
                //标记一定不需要整理的格子的位置
                map[i][j] = iDigit + jDigit <= cnt; //标记可能需要整理的格子的位置
            }
        }
        //然后从0,0位置出发，遍历所有可达的格子
        dfs3(map, 0, 0, reachable, visited);
        //然后我们判断可能需要整理的格子哪些能够真正到达
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (reachable[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean dfs3(boolean[][] map, int startI, int startJ, boolean[][] reachable, boolean[][] visited) {
        reachable[startI][startJ] = true; //标记当前节点可达
        visited[startI][startJ] = true; //标记当前节点已经被搜过
        int[][] directions = new int[][]{{0, 1}, {1, 0}}; //只有向右，和向下移动
        for (int[] direction : directions) {
            int newI = startI + direction[0];
            int newJ = startJ + direction[1];
            if (newI >= 0 && newI < map.length && newJ >= 0 && newJ < map[0].length && map[newI][newJ] && !visited[newI][newJ]) {
                //能走到这里说明新锚点满足条件
                if (dfs3(map, newI, newJ, reachable, visited)) { //那么就以新锚点为起始点进行下一次搜索
                    return true;
                }
            }
        }
        return false;
    }

    //4.以上方案均是深度搜索，试试用广度搜索
    public static int wardrobeFinishing4(int m, int n, int cnt) {
        if (cnt == 0) {
            return 1;
        }
        int count = 1; //因为首节点已经被加进去了，所以，这里最小也需要为1
        boolean[][] visited = new boolean[m][n];
        int[][] directions = new int[][]{{0, 1}, {1, 0}}; //只有向右，和向下移动
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true; //将第一个节点标记为已被访问
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int[] direction : directions) {
                int newI = pos[0] + direction[0];
                int newJ = pos[1] + direction[1];
                //越界和已经被访问过或者大于cnt这种情况时需要跳过
                if (newI < 0 || newI >= m || newJ < 0 || newJ >= n || visited[newI][newJ] || digit(newI) + digit(newJ) > cnt) {
                    continue;
                }
                //如果上述条件都能满足，那么新的节点需要被加入队列中
                queue.offer(new int[]{newI, newJ});
                //同时标定该节点已经被访问过
                visited[newI][newJ] = true;
                //同时记录所有被访问的节点的个数
                count++;
            }
        }
        return count;
    }

    //5.递推可达法
    public static int wardrobeFinishing5(int m, int n, int cnt) {
        if (cnt == 0) {
            return 1;
        }
        boolean[][] blocked = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (digit(i) + digit(j) > cnt) {
                    blocked[i][j] = true;
                } else {
                    if (i == 0 && j == 0) {
                        blocked[i][j] = false;
                        count++;
                        continue;
                    }
                    if ((j - 1 >= 0 && !blocked[i][j - 1]) || (i - 1 >= 0 && !blocked[i - 1][j])) {
                        blocked[i][j] = false;
                        count++;
                    } else {
                        blocked[i][j] = true;
                    }
                }
            }
        }
        return count;
    }
}
