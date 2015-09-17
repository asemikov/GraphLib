package com.asemikov.graphlib;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
            Assert.assertEquals("Null is not allowed as 'vertex' parameter", e.getMessage());
        }

        graph.addVertex("abc");
        graph.addVertex("def");

        try {
            graph.addVertex("ghi");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            Assert.assertEquals("Graph reached maximum vertex count", e.getMessage());
        }
    }

    @Test
    public void testAddEdge() {
        Graph<String> graph = new AdjacencyMatrixGraph<String>(false, 10);

        try {
            graph.addEdge("abc", "def");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Graph has no vertex abc", e.getMessage());
        }
    }

    @Test
    public void testBFS() {
//        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<String>(false, 10);
        Graph<String> graph = new AdjacencyListGraph<String>();

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
        graph.addEdge("C", "D");
        graph.addEdge("C", "F");

        List<Edge<String>> path = graph.getPath("F", "A");

        graph.traverse("A", str -> System.out.println(str));

        if (path.isEmpty()) {
            System.out.println("No path");
        }

        for (Edge<String> edge : path) {
            System.out.println(edge.getSourceVertex() + "->" + edge.getTargetVertex());
        }
    }
}
