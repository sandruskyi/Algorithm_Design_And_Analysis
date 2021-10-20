package tema3_voraces.grafos;

import java.util.*;

public class Dijstrak {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge> graph[] = new List[n + 1];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int w = sc.nextInt();
            graph[v1].add(new Edge(v1, v2, w));
            graph[v2].add(new Edge(v2, v1, w));
        }

        findShortestPath(1, graph);

    }

    public static class Edge {
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static int[] findShortestPath(int v_start, List<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        int[] previous = new int[graph.length];
        Arrays.fill(previous, -1);
        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        Edge fake_edge = new Edge(-1, v_start, 0);
        Comparator<Edge> compareWeight = Comparator.comparing(edge -> edge.weight);
        PriorityQueue<Edge> pq = new PriorityQueue<>(compareWeight);
        pq.add(fake_edge);
        while (!pq.isEmpty()) {
            Edge e1 = pq.poll();
            if (!visited[e1.to]) {
                visited[e1.to] = true;
                distances[e1.to] = e1.weight;
                previous[e1.to] = e1.from;
                for (Edge e2 : graph[e1.to]) {
                    if (e1.weight + e2.weight < distances[e2.to]) {
                        pq.add(new Edge(e2.from, e2.to, e1.weight + e2.weight));
                        distances[e2.to] = e1.weight + e2.weight;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(previous));
        System.out.println(Arrays.toString(distances));
        return previous;
    }
}

/*
7
11
1 3 1
1 4 2
1 7 6
2 5 2
2 6 4
2 7 7
3 7 5
3 4 3
4 5 1
4 6 9
5 7 8







 */