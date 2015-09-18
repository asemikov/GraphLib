package com.asemikov.graphlib;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.Integer;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by alse0514 on 17.09.2015.
 *
 * Abstract graph implementation. Contains common algorithms.
 */
public abstract class AbstractGraph<Vertex> implements Graph<Vertex> {

    protected List<Vertex> vertexList = new ArrayList<>();

    protected int edgesCount = 0;

    protected boolean isDirected = false;

    @Override
    public boolean addVertex(@Nonnull Vertex vertex) {
        Preconditions.checkNotNull(vertex, "Null is not allowed as 'vertex' parameter");

        if (!vertexList.contains(vertex)) {
            vertexList.add(vertex);
            return true;
        }

        return false;
    }


    @Override
    public boolean addEdge(@Nonnull Vertex sourceVertex, @Nonnull Vertex targetVertex) {
        Preconditions.checkNotNull(sourceVertex, "Null is not allowed as 'sourceVertex' parameter");
        Preconditions.checkNotNull(targetVertex, "Null is not allowed as 'targetVertex' parameter");

        if (sourceVertex.equals(targetVertex)) {
            throw new IllegalArgumentException("'sourceVertex' equals to 'targetVertex'. Graph doesn't support loops.");
        }

        int sourceVertexIndex = vertexList.indexOf(sourceVertex);
        int targetVertexIndex = vertexList.indexOf(targetVertex);

        if (sourceVertexIndex == -1) {
            throw new IllegalArgumentException("Graph has no vertex " + sourceVertex);
        }

        if (targetVertexIndex == -1) {
            throw new IllegalArgumentException("Graph has no vertex " + targetVertex);
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

    @Nonnull
    @Override
    public List<Edge<Vertex>> getPath(@Nonnull Vertex sourceVertex, @Nonnull Vertex targetVertex) {
        int sourceVertexIndex = vertexList.indexOf(sourceVertex);
        int targetVertexIndex = vertexList.indexOf(targetVertex);

        if (sourceVertexIndex == -1) {
            throw new IllegalArgumentException("Graph has no vertex " + sourceVertex);
        }

        if (targetVertexIndex == -1) {
            throw new IllegalArgumentException("Graph has no vertex " + targetVertex);
        }

        LinkedList<Edge<Vertex>> path = new LinkedList<>();

        if (sourceVertexIndex != targetVertexIndex) {
            int parent[] = bfs(sourceVertexIndex, null);

            int i = targetVertexIndex;
            while (parent[i] != -1) {
                path.addFirst(new Edge<>(vertexList.get(parent[i]), vertexList.get(i)));
                i = parent[i];
            }
        }

        return path;
    }

    @Override
    public int vertexCount() {
        return vertexList.size();
    }

    @Override
    public int edgesCount() {
        return edgesCount;
    }

    @Override
    public void traverseFrom(@Nonnull Vertex rootVertex, @Nullable Consumer<Vertex> consumer) {
        int rootVertexIndex = vertexList.indexOf(rootVertex);

        if (rootVertexIndex == -1) {
            throw new IllegalArgumentException("Graph has no vertex " + rootVertex);
        }

        bfs(rootVertexIndex, consumer);
    }

    @Override
    public void traverseAll(@Nullable Consumer<Vertex> consumer) {
        boolean visited[] = new boolean[vertexList.size()];

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                bfs(i,
                        vertex -> {
                            int vertexIndex = vertexList.indexOf(vertex);
                            visited[vertexIndex] = true;
                            if (consumer != null) {
                                consumer.accept(vertex);
                            }
                        }
                );
            }
        }
    }

    private int[] bfs(int rootVertexIndex, Consumer<Vertex> consumer) {
        boolean visited[] = new boolean[vertexList.size()];
        int parent[] = new int[vertexList.size()];

        for (int i = 0; i < vertexList.size(); i++) {
            parent[i] = -1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(rootVertexIndex);
        visited[rootVertexIndex] = true;

        while(!queue.isEmpty()) {
            int vertexIndex = queue.remove();
            if (consumer != null) {
                consumer.accept(vertexList.get(vertexIndex));
            }

            for (int i : adjacentVerticesIndexCollection(vertexIndex)) {
                if (!visited[i]) {
                    visited[i] = true;
                    parent[i] = vertexIndex;
                    queue.add(i);
                }
            }
        }

        return parent;
    }

    protected abstract boolean setEdge(int sourceVertexIndex, int targetVertexIndex);

    /**
     * Returns collection of indexes of adjacent vertices
     * @param rootVertexIndex root vertex index
     * @return Nonnull {@link Collection} of {@link Integer} indexes.
     * Returns empty collection if no adjacent vertices were found.
     */
    @Nonnull
    protected abstract Collection<Integer> adjacentVerticesIndexCollection(int rootVertexIndex);
}
