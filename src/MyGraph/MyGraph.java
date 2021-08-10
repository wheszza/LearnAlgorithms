package MyGraph;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author whe
 * @Date 2021/8/10 21:18
 */
public class MyGraph {
    private int[] pre;
    public static void main(String[] args) {
        int[][] edges= {{1, 2, 3}, {1, 3, 4}, {2, 4, 3}, {3, 4, 1}};
        int n = 4;
        MyGraph graph = new MyGraph();
        System.out.println(Arrays.toString(graph.Dijkstra(n, edges)));
        System.out.println(Arrays.toString(graph.BellmanFord(n, edges)));
        int[][] a = graph.Floyd(n, edges);
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                System.out.print(a[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        graph.path(1, 4);
    }

    int[][] Floyd(int n, int[][] edges) {
        int[][] pre = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(pre[i], 0);
        }

        int[][] dis = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dis[i], 1000);
        }
        for(int i = 1; i <= n; i++) {
            dis[i][i] = 0;
        }
        for(int[] edge : edges) {
            dis[edge[0]][edge[1]] = edge[2];
            dis[edge[1]][edge[0]] = edge[2];
        }
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(dis[i][j] > dis[i][k] + dis[k][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                        pre[i][j] = k;
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            System.out.println(Arrays.toString(pre[i]));
        }
        return dis;
    }

    void path(int i, int root) {
        if(pre[i] == root) {
            System.out.print(root);
            System.out.print(" ");
        } else {
            path(pre[i], root);
        }
        System.out.print(i);
        System.out.print(" ");
    }

    int[] BellmanFord(int n, int[][] edges) {
        pre = new int[n + 1];
        int[] dis = new int[n + 1];
        Arrays.fill(dis, 10);
        dis[n] = 0;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < edges.length; i++) {
                if(dis[edges[i][0]] > dis[edges[i][1]] + edges[i][2]) {
                    dis[edges[i][0]] = dis[edges[i][1]] + edges[i][2];
                    pre[edges[i][0]] = edges[i][1];
                }
                if(dis[edges[i][1]] > dis[edges[i][0]] + edges[i][2]) {
                    dis[edges[i][1]] = dis[edges[i][0]] + edges[i][2];
                    pre[edges[i][1]] = edges[i][0];
                }
            }
        }
        return dis;
    }

    int[] Dijkstra(int n, int[][] edges) {
        int[] dis = new int[n + 1];

        ArrayList<ArrayList<Pair<Integer, Integer>>> graph = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Pair<Integer, Integer>>());
        }


        for (int[] edge: edges) {
            Pair<Integer, Integer> temp1 = new Pair<>(edge[1], edge[2]);
            Pair<Integer, Integer> temp2 = new Pair<>(edge[0], edge[2]);
            graph.get(edge[0]).add(temp1);
            graph.get(edge[1]).add(temp2);
        }

        boolean[] vex = new boolean[n + 1];
        Arrays.fill(vex, false);
        Arrays.fill(dis, Integer.MAX_VALUE);
        int root = n;
        dis[root] = 0;
        while(n > 1) {
            int min = Integer.MAX_VALUE;
            int id = root;
            for(int i = 1; i <= n; i++) {
                if(vex[i]) continue;
                if(min > dis[i]) {
                    min = dis[i];
                    id = i;
                }
            }
            vex[id] = true;
            for(Pair<Integer, Integer> u : graph.get(id)) {
                if(dis[id] + u.getValue() < dis[u.getKey()]) {
                    dis[u.getKey()] = dis[id]+ u.getValue();
                }
            }
            n--;
        }
        return dis;
    }
}
