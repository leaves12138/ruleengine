package io.inceptor.drl.graph;

import java.util.List;

public interface Node {
    enum Type{
        EntraceNode, Anode, Bnode, Typenode, ActionNode
    }

    Type getType();

    List<Node> getChildren();
}
