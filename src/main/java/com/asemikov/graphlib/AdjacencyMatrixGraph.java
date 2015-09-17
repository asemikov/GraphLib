package com.asemikov.graphlib;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by asemikov on 16.09.15.
 */
public class AdjacencyMatrixGraph<Vertex> extends AbstractSimpleGraph<Vertex> {

    private boolean adjacencyMatrix[][];

    private int maxVertexCount = 1000;

    public AdjacencyMatrixGraph() {
        vertexList = new ArrayList<Vertex>(maxVertexCount);
        adjacencyMatrix = new boolean[maxVertexCount][maxVertexCount];
    }

    public AdjacencyMatrixGraph(boolean isDirected, int maxVertexCount) {
        this.isDirected = isDirected;
        this.maxVertexCount = maxVertexCount;

        vertexList = new ArrayList<Vertex>(maxVertexCount);
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
/*
    public void bfs(Vertex rootVertex) {
        int rootVertexIndex = vertexList.indexOf(rootVertex);

        if (rootVertexIndex == -1) {
            throw new IllegalArgumentException("Root vertex is not present in graph");
        }

        bfs(rootVertexIndex);
    }*/

    @Override
    protected int[] bfs(int rootVertexIndex) {
        boolean visited[] = new boolean[vertexList.size()];
        int parent[] = new int[vertexList.size()];

        for (int i = 0; i < vertexList.size(); i++) {
            visited[i] = false;
            parent[i] = -1;
        }

        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(rootVertexIndex);
        visited[rootVertexIndex] = true;

        while(!queue.isEmpty()) {
            int vertexIndex = queue.remove();

            for (int i = 0; i < maxVertexCount; i++) {
                if (adjacencyMatrix[vertexIndex][i] && !visited[i]) {
                    visited[i] = true;
                    parent[i] = vertexIndex;
                    queue.add(i);
                }
            }
        }

        return parent;
    }
}
