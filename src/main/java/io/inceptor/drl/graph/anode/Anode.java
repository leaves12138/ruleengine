package io.inceptor.drl.graph.anode;

import io.inceptor.drl.graph.Node;

import java.util.List;

public class Anode implements Node {
    private List<Node> children;

    @Override
    public Type getType(){
        return Type.Anode;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }
}
