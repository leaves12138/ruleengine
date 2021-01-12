package io.inceptor.drl.graph.typenode;

import io.inceptor.drl.graph.Node;

import java.util.List;

public class TypeNode implements Node {
    private List<Node> children;

    private Class type;

    @Override
    public Node.Type getType(){
        return Type.Typenode;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }
}
