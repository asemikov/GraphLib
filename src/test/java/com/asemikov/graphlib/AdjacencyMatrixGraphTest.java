package com.asemikov.graphlib;

import org.junit.Assert;

import static org.junit.Assert.fail;

/**
 * Created by asemikov on 16.09.15.
 */
public class AdjacencyMatrixGraphTest extends AbstractGraphTest {

    protected Graph<String> createGraph(boolean isDirected) {
        return new AdjacencyMatrixGraph<>(isDirected, 10);
    }

    @Override
    public void testAddVertex() {
        super.testAddVertex();

        Graph<String> twoVerticesCapacityGraph = new AdjacencyMatrixGraph<>(false, 2);
        Assert.assertTrue("Adding nonexistent vertex should return true", twoVerticesCapacityGraph.addVertex("A"));
        Assert.assertTrue("Adding nonexistent vertex should return true", twoVerticesCapacityGraph.addVertex("B"));

        try {
            twoVerticesCapacityGraph.addVertex("C");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            Assert.assertEquals("Graph reached maximum vertex count", e.getMessage());
        }
    }
}
