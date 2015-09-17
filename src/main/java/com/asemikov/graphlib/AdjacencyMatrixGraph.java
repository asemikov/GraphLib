package com.asemikov.graphlib;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Consumer;

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

    @Override
    protected int[] bfs(int rootVertexIndex, Consumer<Vertex> consumer) {
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
            if (consumer != null) {
                consumer.accept(vertexList.get(vertexIndex));
            }

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
