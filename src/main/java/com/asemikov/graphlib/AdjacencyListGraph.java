package com.asemikov.graphlib;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by alse0514 on 17.09.2015.
 */
public class AdjacencyListGraph<Vertex> extends AbstractSimpleGraph<Vertex> {

    private List<List<Integer>> adjacencyList = new LinkedList<List<Integer>>();

    @Override
    public boolean addVertex(@Nonnull Vertex vertex) {
        boolean result = super.addVertex(vertex);
        if (result) {
            adjacencyList.add(new LinkedList<Integer>());
        }
        return result;
    }

    @Override
    protected boolean setEdge(int sourceVertexIndex, int targetVertexIndex) {
        if (!adjacencyList.get(sourceVertexIndex).contains(targetVertexIndex)) {
            adjacencyList.get(sourceVertexIndex).add(targetVertexIndex);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int[] bfs(int rootVertexIndex) {
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

            // use PriorityQueue to sort adjacencyList
            PriorityQueue<Integer> sortedAdjacencyList = new PriorityQueue<Integer>(adjacencyList.get(vertexIndex));
            for (int i : sortedAdjacencyList) {
                if (!visited[i]) {
                    visited[i] = true;
                    parent[i] = vertexIndex;
                    queue.add(i);
                }
            }
        }

        return parent;
    }
}
