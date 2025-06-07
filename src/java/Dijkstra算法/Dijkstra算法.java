package Dijkstra算法;

import java.util.Arrays;

public class Dijkstra算法 {
    public static void main(String[] args) {
        // 0 表示自己，INF 表示不连通
        int INF = Integer.MAX_VALUE;
        int[][] graph = {
                {0, 2, 5, INF, INF},
                {2, 0, 3, 1, INF},
                {5, 3, 0, 1, 5},
                {INF, 1, 1, 0, 2},
                {INF, INF, 5, 2, 0}
        };

        int start = 0;
        int[] dist = Dijkstra(graph, start);
        System.out.println("从节点 " + start + " 出发的最短路径:");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("到节点 " + i + " 的最短距离是: " + (dist[i] == INF ? "不可达" : dist[i]));
        }
    }

    public static int[] Dijkstra(int[][] graph, int start) {
        int[] dist = new int[graph.length]; //构建当前节点至其他节点的最小距离的数组
        boolean[] visited = new boolean[graph.length]; //构建当前节点访问与否的数组
        Arrays.fill(dist, Integer.MAX_VALUE); //数组初始值为int最大值，表示无穷大
        dist[start] = 0; //如果是到当前节点本身的最小距离，应该是0
        for (int i = 0; i < graph.length; i++) { //依次填满dist
            //1.首先寻找没有被访问过的,且距离最小的点
            int u = -1, min = Integer.MAX_VALUE;
            for (int j = 0; j < graph.length; j++) {
                if (!visited[j] && dist[j] < min) {
                    u = j; //找到具体节点
                    min = dist[j]; //更新最小值
                }
            }
            if (u == -1) {
                break;
            }
            visited[u] = true; //初始的时候肯定u==start, 将找到的节点设置为已访问
            //2.然后从找到的节点开始，寻找临近的且未访问的节点，如果找到的节点距离其他未访问的节点长度不为无穷大，那么就将其与dist[v]相比较，
            for (int v = 0; v < graph.length; v++) {
                if (!visited[v] && graph[u][v] != 0 && graph[u][v] != Integer.MAX_VALUE) { //v节点未被访问
                    dist[v] = Math.min(dist[v], dist[u] + graph[u][v]);  //dist[u] + graph[u][v]为从start节点到u节点的最小路径+与u相邻的节点的路径 = 从start节点到v节点的最短路径
                }
            }
        }
        return dist;
    }
}
