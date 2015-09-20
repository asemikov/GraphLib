package com.asemikov.graphlib;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by asemikov on 21.09.15.
 */
public abstract class AbstractGraphTest {

    protected abstract Graph<String> createGraph(boolean isDirected);

    protected void initTestGraph(Graph<String> graph) {
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
    }

    @Test
    public void testAddVertexFail() {
        Graph<String> graph = createGraph(false);

        try {
            graph.addVertex(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            Assert.assertEquals("Null is not allowed as 'vertex' parameter", e.getMessage());
        }
    }

    @Test
    public void testAddEdgeFail() {
        Graph<String> graph = createGraph(false);
        graph.addVertex("A");
        graph.addVertex("B");

        try {
            graph.addEdge(null, null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            Assert.assertEquals("Null is not allowed as 'sourceVertex' parameter", e.getMessage());
        }

        try {
            graph.addEdge("A", null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            Assert.assertEquals("Null is not allowed as 'targetVertex' parameter", e.getMessage());
        }

        try {
            graph.addEdge("B", "C");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Graph has no vertex C", e.getMessage());
        }

        try {
            graph.addEdge("C", "B");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Graph has no vertex C", e.getMessage());
        }

        try {
            graph.addEdge("B", "B");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("'sourceVertex' equals to 'targetVertex'. Graph doesn't support loops.", e.getMessage());
        }
    }

    @Test
    public void traverseFromTest() {
        Graph<String> graph = createGraph(false);
        initTestGraph(graph);

        List<String> actualList = new ArrayList<>();

        try {
            graph.traverseFrom("J", actualList::add);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Graph has no vertex J", e.getMessage());
        }

        graph.traverseFrom("A", actualList::add);
        Assert.assertEquals(Arrays.asList("A", "B", "D", "C", "E", "F"), actualList);

        actualList.clear();
        graph.traverseFrom("F", actualList::add);
        Assert.assertEquals(Arrays.asList("F", "C", "E", "B", "D", "A"), actualList);

        graph = createGraph(true);
        initTestGraph(graph);

        actualList.clear();

        graph.traverseFrom("A", actualList::add);
        Assert.assertEquals(Arrays.asList("A", "B", "D", "C", "E", "F"), actualList);

        actualList.clear();
        graph.traverseFrom("F", actualList::add);
        Assert.assertEquals(Arrays.asList("F"), actualList);
    }

    @Test
    public void traverseAllTest() {
        Graph<String> graph = createGraph(false);
        initTestGraph(graph);

        List<String> actualList = new ArrayList<>();

        graph.traverseAll(actualList::add);
        Assert.assertEquals(Arrays.asList("A", "B", "D", "C", "E", "F", "X", "Y", "Z"), actualList);

        graph = createGraph(true);
        initTestGraph(graph);

        actualList.clear();
        graph.traverseAll(actualList::add);
        Assert.assertEquals(Arrays.asList("A", "B", "D", "C", "E", "F", "X", "Y", "Z"), actualList);
    }

    @Test
    public void getPathTest() {
        Graph<String> graph = createGraph(false);
        initTestGraph(graph);

        List<Edge<String>> actualList = graph.getPath("A", "F");
        Assert.assertEquals(Arrays.asList(
                new Edge<>("A", "B"),
                new Edge<>("B", "C"),
                new Edge<>("C", "F")
        ), actualList);

        actualList.clear();
        actualList = graph.getPath("F", "A");
        Assert.assertEquals(Arrays.asList(
                new Edge<>("F", "C"),
                new Edge<>("C", "B"),
                new Edge<>("B", "A")
        ), actualList);

        graph = createGraph(true);
        initTestGraph(graph);

        actualList.clear();
        actualList = graph.getPath("A", "F");
        Assert.assertEquals(Arrays.asList(
                new Edge<>("A", "B"),
                new Edge<>("B", "C"),
                new Edge<>("C", "F")
        ), actualList);

        actualList.clear();
        actualList = graph.getPath("F", "A");
        Assert.assertEquals(Arrays.asList(), actualList);

    }
}
