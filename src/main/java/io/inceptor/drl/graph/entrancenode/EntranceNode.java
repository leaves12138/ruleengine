package io.inceptor.drl.graph.entrancenode;

import io.inceptor.drl.graph.Node;

import java.util.List;

public class EntranceNode implements Node {

    private List<Node> children;

    @Override
    public Type getType(){
        return Type.EntraceNode;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }
}
