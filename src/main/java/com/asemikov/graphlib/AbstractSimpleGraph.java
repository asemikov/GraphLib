package com.asemikov.graphlib;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by alse0514 on 17.09.2015.
 */
public abstract class AbstractSimpleGraph<Vertex> implements Graph<Vertex> {

    protected List<Vertex> vertexList = new ArrayList<Vertex>();

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

    @Override
    public List<Edge<Vertex>> getPath(Vertex sourceVertex, Vertex targetVertex) {
        int sourceVertexIndex = vertexList.indexOf(sourceVertex);
        int targetVertexIndex = vertexList.indexOf(targetVertex);

        if (sourceVertexIndex == -1) {
            throw new IllegalArgumentException("Graph has no vertex " + sourceVertex);
        }

        if (targetVertexIndex == -1) {
            throw new IllegalArgumentException("Graph has no vertex " + targetVertex);
        }

        LinkedList<Edge<Vertex>> path = new LinkedList<Edge<Vertex>>();

        if (sourceVertexIndex != targetVertexIndex) {
            int parent[] = bfs(sourceVertexIndex, null);

            int i = targetVertexIndex;
            while (parent[i] != -1) {
                path.addFirst(new Edge<Vertex>(vertexList.get(parent[i]), vertexList.get(i)));
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
    public void traverse(@Nonnull Vertex rootVertex, @Nullable Consumer<Vertex> consumer) {
        int rootVertexIndex = vertexList.indexOf(rootVertex);

        if (rootVertexIndex == -1) {
            throw new IllegalArgumentException("Graph has no vertex " + rootVertex);
        }

        bfs(rootVertexIndex, consumer);
    }

    protected abstract boolean setEdge(int sourceVertexIndex, int targetVertexIndex);
    protected abstract int[] bfs(int rootVertexIndex, Consumer<Vertex> consumer);
}
