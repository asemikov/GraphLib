package com.asemikov.graphlib;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by asemikov on 16.09.15.
 *
 * Implements unweighted graph based on adjacency matrix.
 */
public class AdjacencyMatrixGraph<Vertex> extends AbstractGraph<Vertex> {

    private boolean adjacencyMatrix[][];

    private int maxVertexCount = 1000;

    /**
     * @param isDirected flag to set the graph directed or undirected
     * @param maxVertexCount maximum vertex capacity of the graph
     */
    public AdjacencyMatrixGraph(boolean isDirected, int maxVertexCount) {
        super(isDirected);

        this.maxVertexCount = maxVertexCount;

        vertexList = new ArrayList<>(maxVertexCount);
        adjacencyMatrix = new boolean[maxVertexCount][maxVertexCount];
    }

    @Override
    public boolean addVertex(@Nonnull Vertex vertex) {
        if (vertexList.size() >= maxVertexCount) {
            throw new IndexOutOfBoundsException("Graph reached maximum vertex count");
        }

        return super.addVertex(vertex);
    }

    @Override
    protected boolean setEdge(int sourceVertexIndex, int targetVertexIndex) {
        if (!adjacencyMatrix[sourceVertexIndex][targetVertexIndex]) {
            adjacencyMatrix[sourceVertexIndex][targetVertexIndex] = true;
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
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < vertexList.size(); i++) {
            if (adjacencyMatrix[rootVertexIndex][i]) {
                indices.add(i);
            }
        }

        return indices;
    }
}
