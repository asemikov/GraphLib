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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge<?> edge = (Edge<?>) o;

        if (sourceVertex != null ? !sourceVertex.equals(edge.sourceVertex) : edge.sourceVertex != null) return false;
        return !(targetVertex != null ? !targetVertex.equals(edge.targetVertex) : edge.targetVertex != null);

    }

    @Override
    public int hashCode() {
        int result = sourceVertex != null ? sourceVertex.hashCode() : 0;
        result = 31 * result + (targetVertex != null ? targetVertex.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "sourceVertex=" + sourceVertex +
                ", targetVertex=" + targetVertex +
                '}';
    }
}
