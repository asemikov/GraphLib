package com.asemikov.graphlib;

/**
 * Created by asemikov on 17.09.15.
 */
public class Edge<Vertex> {

    private Vertex sourceVertex;

    private Vertex targetVertex;

    public Edge(Vertex sourceVertex, Vertex targetVertex) {
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
    }

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }
}
