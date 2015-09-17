package com.asemikov.graphlib;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by asemikov on 16.09.15.
 */
public class AdjacencyMatrixGraphTest {

    @Test
    public void testAddVertex() {
        Graph<String> graph = new AdjacencyMatrixGraph<String>(false, 2);

        try {
            graph.addVertex(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            Assert.assertEquals(e.getMessage(), "Null is not allowed as 'vertex' parameter");
        }

        graph.addVertex("abc");
        graph.addVertex("def");

        try {
            graph.addVertex("ghi");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            Assert.assertEquals(e.getMessage(), "Graph reached maximum vertex count");
        }
    }

    @Test
    public void testAddEdge() {
        Graph<String> graph = new AdjacencyMatrixGraph<String>(false, 10);

        try {
            graph.addEdge("abc", "def");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Vertex 'sourceVertex' is not present in graph");
        }
    }

    @Test
    public void testBFS() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<String>(false, 10);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        graph.addEdge("E", "F");

        graph.addEdge("A", "D");
        graph.addEdge("A", "F");

        graph.bfs("C");
    }

}
