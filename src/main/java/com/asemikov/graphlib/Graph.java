package com.asemikov.graphlib;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by asemikov on 16.09.15.
 */
public interface Graph<Vertex> {

    /**
     * Adds the specified vertex to this graph if not already present.
     * More formally, adds the specified vertex, v, to this graph if this graph contains no vertex u such that u.equals(v).
     * If this graph already contains such vertex, the call leaves this graph unchanged and returns false.
     * @param vertex vertex to be added to this graph.
     * @return <code>true</code> if this graph did not already contain the specified vertex.
     * @throws NullPointerException if the specified vertex is null.
     */
    boolean addVertex(@Nonnull Vertex vertex);

    /**
     * Creates a new edge in this graph, going from the source vertex to the target vertex, and returns the created edge.
     * <p/>Edge-multiplicity is not allowed.
     * If the graph already contains an edge from the specified source to the specified target,
     * than this method does not change the graph and returns false.
     * <p/>Loops are not allowed.
     * If source vertex equals to target vertex IllegalArgumentException is thrown.
     * <p/>The source and target vertices must already be contained in this graph.
     * If they are not found in graph IllegalArgumentException is thrown.
     * @param sourceVertex source vertex of the edge.
     * @param targetVertex target vertex of the edge.
     * @return <code>false</code> if the graph already contains an edge from the specified source to the specified target.
     * Otherwise <code>true</code>
     * @throws NullPointerException if any of the specified vertices is null.
     * @throws IllegalArgumentException if source or target vertices are not found in the graph.
     * @throws IllegalArgumentException if source vertex equals to target vertex.
     */
    boolean addEdge(@Nonnull Vertex sourceVertex, @Nonnull Vertex targetVertex);

    /**
     * Finds optimal path from source vertex to target vertex in the graph using 'Breadth-first search' algorithm.
     * Returns sorted list of {@link Edge} connecting source and target vertices.
     * Returns empty list if path is not found.
     * @param sourceVertex source vertex of the path.
     * @param targetVertex target vertex of the path.
     * @return sorted list of {@link Edge} connecting source and target vertices.
     * Or empty list if path is not found.
     * @throws NullPointerException if any of the specified vertices is null.
     * @throws IllegalArgumentException if source or target vertices are not found in the graph.
     */
    @Nonnull
    List<Edge<Vertex>> getPath(@Nonnull Vertex sourceVertex, @Nonnull Vertex targetVertex);

    /**
     * Traverses vertices in the graph from specified vertex using 'Breadth-first search' algorithm.
     * Applies consumer (if specified) to every discovered vertex.
     * @param rootVertex root vertex to be used as start point.
     * @param consumer {@link Consumer} implementation to be applied to every discovered vertex in traverse.
     * @throws NullPointerException if the specified vertex is null.
     * @throws IllegalArgumentException if specified vertex is not found in the graph.
     */
    void traverseFrom(@Nonnull Vertex rootVertex, @Nullable Consumer<Vertex> consumer);

    /**
     * Traverses all vertices in the graph using 'Breadth-first search' algorithm.
     * Applies consumer (if specified) to every discovered vertex.
     * @param consumer {@link Consumer} implementation to be applied to every discovered vertex in traverse.
     */
    void traverseAll(@Nullable Consumer<Vertex> consumer);

}
