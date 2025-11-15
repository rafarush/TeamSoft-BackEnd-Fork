package com.tesis.teamsoft.presentation.dto;

public class DefaultTreeNode extends TreeNode {

    public DefaultTreeNode() {
        super();
    }

    public DefaultTreeNode(Object data) {
        super(data);
    }

    public DefaultTreeNode(Object data, TreeNode parent) {
        super(data, parent);
    }

    public DefaultTreeNode(Object data, String type) {
        super(data, type);
    }

    // Constructor para replicar el comportamiento específico de PrimeFaces
    public DefaultTreeNode(String formattedEval) {
        super(formattedEval);
    }
}
