package com.asemikov.graphlib;

/**
 * Created by asemikov on 17.09.15.
 */
public class Edge<Vertex> {

    private Vertex sourceVertex;

    private Vertex targetVertex;

    public Vertex getSourceVertex() {
        return sourceVertex;
    }

    public void setSourceVertex(Vertex sourceVertex) {
        this.sourceVertex = sourceVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }
}
