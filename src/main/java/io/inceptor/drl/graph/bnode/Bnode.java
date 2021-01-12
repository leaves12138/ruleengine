package io.inceptor.drl.graph.bnode;

import io.inceptor.drl.graph.Node;

import java.util.List;

public class Bnode implements Node {
    private List<Node> children;

    @Override
    public Type getType(){
        return Type.Bnode;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }
}
