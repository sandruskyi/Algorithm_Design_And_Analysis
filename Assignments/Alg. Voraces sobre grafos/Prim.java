package tema3_voraces.grafos;

import java.io.InputStream;
import java.util.*;

public class Prim {

    /**
     * Arista de un grafo ponderado no dirigido.
     *
     * <p><b>Importante:</b> Al ser no dirigido se considera que las
     * aristas (u,v) y (v,u) son iguales, sin importar su peso. La
     * implementacion de equals() y hashcode() esta hecha de
     * acuerdo con este criterio.
     * </p>
     */
    public static class Edge {
        public int from;
        public int to;
        public int weight;
        //Importante: la implementacion tando de equals() como de hashcode()
        //considera iguales tanto las aristas (u,v) como (v,u). Ignora su
        //peso en la comparacion.

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Edge edge = (Edge) o;
//            boolean direct = from == edge.from &&
//                    to == edge.to &&
//                    weight == edge.weight;
//            boolean reverse = from == edge.to &&
//                    to == edge.from;
//            return direct || reverse;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(Math.min(from, to), Math.max(from, to));
//        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }


    /**
     * Algoritmo de prim. Obtiene un arbol de recubrimiento minimo a partir del vertice
     * de inicio.
     * <p>Complejidad: O(E·logV)</p>
     *
     * @param v_start vertice inicial
     * @param graph   grafo ponderado no dirigido
     * @return conjunto de aristas del arbol de recubrimiento
     */

    public static Set<Edge> prim(int v_start, List<Edge>[] graph) {
        Set<Edge> edges = new HashSet<>();
        boolean visit[] = new boolean[graph.length];//(n+1)
        int dmax[] = new int[graph.length];
        Arrays.fill(dmax, Integer.MAX_VALUE);

        //Arista ficticia para inicializar el recorrido (se debe eliminanr al final)
        Edge fake_edge = new Edge(-1, v_start, 0);
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(edge -> edge.weight));

        pq.add(fake_edge);
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (!visit[edge.to]) {
                visit[edge.to] = true;
                edges.add(edge);
                for (Edge adj : graph[edge.to]) {
                    if (!visit[adj.to] && adj.weight < dmax[adj.to]) {
                        dmax[adj.to] = adj.weight;
                        pq.add(adj);
                    }
                }
            }
        }
        edges.remove(fake_edge);
        return edges;
    }

    public static void main(String[] args) {
        // Leer grafo desde la entrada estandar (teclado)
        List<Edge>[] graph = readGraph(System.in);

        Set<Edge> edgeSet = prim(1, graph);

        // Visualizar solucion
        int cost = 0;
        for (Edge edge : edgeSet) {
            cost += edge.weight;
            System.out.printf("%2d %2d %2d\n", edge.from, edge.to, edge.weight);
        }
        System.out.println("Total weight:" + cost);
    }


    /**
     * Lee los datos del grafo desde la entrada suministrada.
     * <p>
     * Formato: Un entero n con el numero de vertices, un entero m con el numero
     * de aristas, y a continuación m aristas, cada una con tres numeros los
     * vertices (u,v) y su peso w.
     *
     * <pre>
     * n
     * m
     * u_1 v_1 w_1
     * u_2 v_2 w_2
     * ...
     * u_m v_m w_m
     * </pre>
     * <p>
     * Ejemplo:
     * <pre>
     * 5
     * 7
     * 1 2 7
     * 1 3 6
     * 3 2 1
     * 1 4 10
     * 3 4 15
     * 3 5 3
     * 4 5 12
     * </pre>
     *
     * @param in flujo de entrada
     * @return Grafo ponderado no dirigido como lista de adyacencia.
     */
    private static List<Edge>[] readGraph(InputStream in) {
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int c = sc.nextInt();

        List<Edge> graph[] = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < c; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();

            Edge edgeUV = new Edge(u, v, weight);
            Edge edgeVU = new Edge(v, u, weight);
            graph[u].add(edgeUV);
            graph[v].add(edgeVU);
        }
        return graph;
    }

}


/*
Ejemplos de entrada/salida
>input
5
7
1 2 7
1 3 6
3 2 1
1 4 10
3 4 15
3 5 3
4 5 12
>output
20

>input
4
3
1 3 2
1 4 3
3 4 5
>output
5


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