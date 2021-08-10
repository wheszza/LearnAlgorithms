package MyGraph;

import java.util.Arrays;

/**
 * @Author whe
 * @Date 2021/8/10 11:30
 */
public class ShortestPath {
    public static void main(String[] args) {
        ShortestPath test = new ShortestPath();
        int[][] edges = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        int M = 4, N = 5;
        AdjList graph = new AdjList(M, N);
        for (int [] edge : edges) {
            graph.add(edge[0], edge[1], edge[2]);
        }
        int[] dis = new int[N];
        test.dijkstra(2, dis, graph);
        System.out.println(Arrays.toString(dis));
    }
    void dijkstra(int start, int[] dis, AdjList graph){
        int N = dis.length;
        int INF = 0x3f3f3f3f;
        boolean[] visit = new boolean[N];
        Arrays.fill(dis, INF);
        dis[start] = 0;
        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;
            int id = -1;
            for (int j = 0; j < N; j++) {
                if (!visit[j] && dis[j] < min) {
                    min = dis[j];
                    id = j;
                }
            }
            if (id == -1) break;
            visit[id] = true;
            for (int j = graph.head[id]; j != -1; j = graph.next[j]) {
                int tar = graph.edge[j];
                int weight = graph.weight[j];
                if (dis[tar] > dis[id] + weight) {
                    dis[tar] = dis[id] + weight;
                }
            }
        }
    }
    void bellmanFord(int start, int[] dis, AdjList graph) {
        int m = graph.M;
        int n = graph.N;
        int INF = 0x3f3f3f3f;
        Arrays.fill(dis, INF);
        dis[start] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = graph.head[j]; k != -1; k = graph.next[k]) {
                    int tar = graph.edge[k];
                    int weight = graph.weight[k];
                    if (dis[tar] > dis[j] + weight) {
                        dis[tar] = dis[j] + weight;
                    }
                }
            }
        }
    }
}
