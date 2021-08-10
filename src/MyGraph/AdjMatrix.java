package MyGraph;

/**
 * @Author whe
 * @Date 2021/8/9 9:55
 */
public class AdjMatrix {
    int N; //点数量
    int[][] matrix; //邻接矩阵
    AdjMatrix(int n) {
        N = n;
        matrix = new int[N][N];
    }
    void add(int src, int tar, int w) {
        matrix[src][tar] = w;
    }
}
