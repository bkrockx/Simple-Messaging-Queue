package com.phonepe.model;

import java.util.LinkedList;

public class ConsumerDependencyGraph {
    private int graphSize;
    private LinkedList<Integer> dependencyMatrix[];

    public ConsumerDependencyGraph(int graphSize) {
        this.graphSize = graphSize;
        this.dependencyMatrix = new LinkedList[graphSize + 1];
        for(int i = 0; i <= graphSize; i++){
            dependencyMatrix[i] = new LinkedList();
        }
    }

    public void addDependents(int parentConsumerId,int childConsumerId){
        dependencyMatrix[parentConsumerId].add(childConsumerId);
    }

    public int getGraphSize() {
        return graphSize;
    }

    public LinkedList<Integer>[] getDependencyMatrix() {
        return dependencyMatrix;
    }

    public LinkedList<Integer> getDependencies(Integer sourceId){
        return dependencyMatrix[sourceId];
    }
}
