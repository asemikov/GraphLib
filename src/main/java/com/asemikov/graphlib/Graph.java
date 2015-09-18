package com.asemikov.graphlib;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by asemikov on 16.09.15.
 */
public interface Graph<Vertex> {

    boolean addVertex(@Nonnull Vertex vertex);

    boolean addEdge(@Nonnull Vertex sourceVertex, @Nonnull Vertex targetVertex);

    @Nonnull
    List<Edge<Vertex>> getPath(@Nonnull Vertex sourceVertex, @Nonnull Vertex targetVertex);

    int vertexCount();

    int edgesCount();

    void traverseFrom(@Nonnull Vertex rootVertex, @Nullable Consumer<Vertex> consumer);

}
