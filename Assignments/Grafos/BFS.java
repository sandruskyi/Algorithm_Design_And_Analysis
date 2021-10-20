package tema2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    public static List<Integer> breadthFirstSearch(List<Integer>[] g, int v) {
        int n = g.length;
        List<Integer> traversal = new ArrayList<>(n);
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        visited[v] = true;
        traversal.add(v);
        q.add(v);
        while (!q.isEmpty()) {
            int aux = q.remove();
            for (int adj : g[aux]) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    traversal.add(adj);
                    q.add(adj);
                }
            }
        }
        return traversal;
    }

    public static List<Integer>[] breadthFirstSearch(List<Integer>[] g) {
        int n = g.length;
        List<Integer> traversals[] = new List[n];
        for (int v = 0; v < n; v++) {
            traversals[v] = breadthFirstSearch(g, v);
        }
        return traversals;
    }
}
