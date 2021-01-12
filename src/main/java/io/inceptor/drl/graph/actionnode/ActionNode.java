package io.inceptor.drl.graph.actionnode;

import io.inceptor.drl.graph.Node;

import java.util.List;

public class ActionNode implements Node {
    private List<Node> children;

    @Override
    public Type getType(){
        return Type.ActionNode;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }
}
