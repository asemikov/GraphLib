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
        Graph<String> graph = new AdjacencyMatrixGraph<>(false, 2);

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
        Graph<String> graph = new AdjacencyMatrixGraph<>(false, 10);

        try {
            graph.addEdge("abc", "def");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Graph has no vertex abc", e.getMessage());
        }

        try {
            graph.addEdge("abc", "abc");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("'sourceVertex' equals to 'targetVertex'. Graph doesn't support loops.", e.getMessage());
        }
    }

    @Test
    public void testBFS() {
//        Graph<String> graph = new AdjacencyMatrixGraph<>(false, 10);
        Graph<String> graph = new AdjacencyListGraph<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addVertex("X");
        graph.addVertex("Y");
        graph.addVertex("Z");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        graph.addEdge("E", "F");

        graph.addEdge("A", "D");
        graph.addEdge("C", "D");
        graph.addEdge("C", "F");

        graph.addEdge("X", "Y");
        graph.addEdge("Y", "Z");

        System.out.println("Traverse from A");
        graph.traverseFrom("A", System.out::println);

        System.out.println("\nTraverse from Z");
        graph.traverseFrom("Z", System.out::println);

        System.out.println("\nTraverse all");
        graph.traverseAll(System.out::println);

        System.out.println("\nFind path from F to A");
        List<Edge<String>> path = graph.getPath("F", "A");
        if (path.isEmpty()) {
            System.out.println("No path");
        }

        for (Edge<String> edge : path) {
            System.out.println(edge.getSourceVertex() + "->" + edge.getTargetVertex());
        }
    }
}
