package tema2.tests;

import tema2.DFS;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DFSTest {

    @org.junit.jupiter.api.Test
    void depthFirstSearchUndirected1() {
        int n = 9;
        // Create the graph with n nodes
        List<Integer>[] g = new List[n+1];
        for (int i = 1; i <= n; i++) {
            // Initialize each list of adjacent vertices
            g[i] = new ArrayList<>(n);
        }
        /*
        Each edge is added in both directions, since it is
        a non-directed graph
         */
        g[1].add(2); g[2].add(1);
        g[1].add(4); g[4].add(1);
        g[1].add(8); g[8].add(1);
        g[2].add(3); g[3].add(2);
        g[2].add(4); g[4].add(2);
        g[3].add(4); g[4].add(3);
        g[3].add(5); g[5].add(3);
        g[4].add(7); g[7].add(4);
        g[5].add(6); g[6].add(5);
        g[6].add(7); g[7].add(6);
        g[7].add(9); g[9].add(7);
        g[8].add(9); g[9].add(8);

        int[] expected = new int[]{1, 2, 3, 4, 7, 6, 5, 9, 8};
        List<Integer> traversals = DFS.depthFirstSearch(g, 1);
        assertEquals(expected.length, traversals.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], traversals.get(i));
        }
    }

    @org.junit.jupiter.api.Test
    void depthFirstSearchUndirected2() {
        int n = 8;
        // Create the graph with n nodes
        List<Integer>[] g = new List[n+1];
        for (int i = 1; i <= n; i++) {
            // Initialize each list of adjacent vertices
            g[i] = new ArrayList<>(n);
        }
        /*
        Each edge is added in both directions, since it is
        a non-directed graph
         */
        g[1].add(2); g[2].add(1);
        g[1].add(3); g[3].add(1);
        g[1].add(4); g[4].add(1);
        g[2].add(3); g[3].add(2);
        g[2].add(5); g[5].add(2);
        g[2].add(6); g[6].add(2);
        g[3].add(6); g[6].add(3);
        g[4].add(7); g[7].add(4);
        g[4].add(8); g[8].add(4);
        g[5].add(6); g[6].add(5);
        g[7].add(8); g[8].add(7);

        int[] expected = new int[]{1, 2, 3, 6, 5, 4, 7, 8};
        List<Integer> traversals = DFS.depthFirstSearch(g, 1);
        assertEquals(expected.length, traversals.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], traversals.get(i));
        }
    }

    @org.junit.jupiter.api.Test
    void depthFirstSearchDirected() {
        int n = 8;
        // Create the graph with n nodes
        List<Integer>[] g = new List[n+1];
        for (int i = 1; i <= n; i++) {
            // Initialize each list of adjacent vertices
            g[i] = new ArrayList<>(n);
        }
        /*
        Each edge is added in a single direction, since it is
        a directed graph
         */
        g[1].add(2);
        g[1].add(4);
        g[1].add(8);
        g[2].add(3);
        g[3].add(1);
        g[4].add(8);
        g[5].add(2);
        g[5].add(6);
        g[6].add(5);
        g[6].add(3);
        g[8].add(7);

        int[] expected = new int[]{1, 2, 3, 4, 8, 7};
        List<Integer> traversals = DFS.depthFirstSearch(g, 1);
        assertEquals(expected.length, traversals.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], traversals.get(i));
        }
    }
}