package com.asemikov.graphlib;

/**
 * Created by asemikov on 16.09.15.
 */
public class AdjacencyListGraphTest extends AbstractGraphTest {

    protected Graph<String> createGraph(boolean isDirected) {
        return new AdjacencyListGraph<>(isDirected);
    }

}
