package com.asemikov.graphlib;

import java.util.List;

/**
 * Created by asemikov on 16.09.15.
 */
public interface Graph<Vertex> {

    boolean addVertex(Vertex vertex);

    boolean addEdge(Vertex sourceVertex, Vertex targetVertex);

    List<Edge<Vertex>> getPath(Vertex sourceVertex, Vertex targetVertex);

    int vertexCount();

    int edgesCount();

}
