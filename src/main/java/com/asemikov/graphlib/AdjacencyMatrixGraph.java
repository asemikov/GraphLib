package com.asemikov.graphlib;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by asemikov on 16.09.15.
 */
public class AdjacencyMatrixGraph<Vertex> implements Graph<Vertex> {

    private boolean isDirected = false;

    private boolean adjacencyMatrix[][];

    private List<Vertex> vertexList;

    private int maxVertexCount = 1000;

    private int edgesCount = 0;

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

    public boolean addVertex(@Nonnull Vertex vertex) {
        Preconditions.checkNotNull(vertex, "Null is not allowed as 'vertex' parameter");

        if (vertexList.size() >= maxVertexCount) {
            throw new IndexOutOfBoundsException("Graph reached maximum vertex count");
        }

        if (!vertexList.contains(vertex)) {
            vertexList.add(vertex);
            return true;
        }

        return false;
    }

    public boolean addEdge(@Nonnull Vertex sourceVertex, @Nonnull Vertex targetVertex) {
        Preconditions.checkNotNull(sourceVertex, "Null is not allowed as 'sourceVertex' parameter");
        Preconditions.checkNotNull(targetVertex, "Null is not allowed as 'targetVertex' parameter");

        if (sourceVertex.equals(targetVertex)) {
            throw new IllegalArgumentException("'sourceVertex' equals to 'targetVertex'. Graph doesn't support loops.");
        }

        int sourceVertexIndex = vertexList.indexOf(sourceVertex);
        int targetVertexIndex = vertexList.indexOf(targetVertex);

        if (sourceVertexIndex == -1) {
            throw new IllegalArgumentException("Vertex 'sourceVertex' is not present in graph");
        }

        if (targetVertexIndex == -1) {
            throw new IllegalArgumentException("Vertex 'targetVertexIndex' is not present in graph");
        }

        boolean result = setEdge(sourceVertexIndex, targetVertexIndex);
        if (result && !isDirected) {
            result = setEdge(targetVertexIndex, sourceVertexIndex);
        }

        if (result) {
            edgesCount++;
        }

        return result;
    }

    public List<Edge<Vertex>> getPath(Vertex sourceVertex, Vertex targetVertex) {
        return null;
    }

    public int vertexCount() {
        return vertexList.size();
    }

    public int edgesCount() {
        return edgesCount;
    }

    private boolean setEdge(int sourceVertexIndex, int targetVertexIndex) {
        if (!adjacencyMatrix[sourceVertexIndex][targetVertexIndex]) {
            adjacencyMatrix[sourceVertexIndex][targetVertexIndex] = true;
            return true;
        } else {
            return false;
        }
    }

    public void bfs(Vertex rootVertex) {
        int rootVertexIndex = vertexList.indexOf(rootVertex);

        if (rootVertexIndex == -1) {
            throw new IllegalArgumentException("Root vertex is not present in graph");
        }

        bfs(rootVertexIndex);
    }

    private void bfs(int rootVertexIndex) {
        boolean visited[] = new boolean[vertexList.size()];

        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(rootVertexIndex);
        visited[rootVertexIndex] = true;

        while(!queue.isEmpty()) {
            int vertexIndex = queue.remove();

            System.out.println(vertexList.get(vertexIndex));

            for (int i = 0; i < maxVertexCount; i++) {
                if (adjacencyMatrix[vertexIndex][i] && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
