package com.asemikov.graphlib;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by alse0514 on 17.09.2015.
 *
 * Implements unweighted graph based on adjacency list.
 */
public class AdjacencyListGraph<Vertex> extends AbstractGraph<Vertex> {

    private List<List<Integer>> adjacencyList = new ArrayList<>();

    /**
     * @param isDirected flag to set the graph directed or undirected
     */
    public AdjacencyListGraph(boolean isDirected) {
        super(isDirected);
    }

    @Override
    public boolean addVertex(@Nonnull Vertex vertex) {
        boolean result = super.addVertex(vertex);
        if (result) {
            adjacencyList.add(new ArrayList<>());
        }
        return result;
    }

    @Override
    protected boolean setEdge(int sourceVertexIndex, int targetVertexIndex) {
        if (!adjacencyList.get(sourceVertexIndex).contains(targetVertexIndex)) {
            adjacencyList.get(sourceVertexIndex).add(targetVertexIndex);
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p/>{@inheritDoc}
     */
    @Nonnull
    @Override
    protected Collection<Integer> adjacentVerticesIndexCollection(int rootVertexIndex) {
        return new PriorityQueue<>(adjacencyList.get(rootVertexIndex));
    }
}
