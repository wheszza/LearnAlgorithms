package MyGraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * @Author whe
 * @Date 2021/8/9 9:54
 */
public class AdjList {
    int M; //边数量
    int N; //点数量
    int[] head;
    int[] next;
    int[] edge;
    int[] weight;
    int idx;
    AdjList(int m, int n) {
        M = m;
        N = n;
        head = new int[N];
        Arrays.fill(head, -1);
        next = new int[M];
        edge = new int[M];
        weight = new int[M];
        idx = 0;
    }

    void add(int src, int tar, int w) {
        edge[idx] = tar;
        weight[idx] = w;
        next[idx] = head[src];
        head[src] = idx;
        idx++;
    }
}
