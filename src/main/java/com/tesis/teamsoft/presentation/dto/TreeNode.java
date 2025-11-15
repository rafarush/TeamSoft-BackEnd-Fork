package com.tesis.teamsoft.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class TreeNode implements Serializable {
    private Object data;
    private List<TreeNode> children;
    private TreeNode parent;
    private String type;
    private boolean expanded;
    private boolean selectable;
    private boolean selected;
    private String rowKey;

    // Constructores
    public TreeNode() {
        this.children = new ArrayList<>();
        this.expanded = false;
        this.selectable = true;
    }

    public TreeNode(Object data) {
        this();
        this.data = data;
    }

    public TreeNode(Object data, TreeNode parent) {
        this();
        this.data = data;
        this.parent = parent;
    }

    public TreeNode(Object data, String type) {
        this();
        this.data = data;
        this.type = type;
    }

    // Métodos principales para replicar PrimeFaces TreeNode
    public void setChildren(List<TreeNode> children) {
        this.children = children;
        if (children != null) {
            for (TreeNode child : children) {
                child.setParent(this);
            }
        }
    }

    public void addChild(TreeNode child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        child.setParent(this);
        children.add(child);
    }

    public void removeChild(TreeNode child) {
        if (children != null) {
            children.remove(child);
            child.setParent(null);
        }
    }

    public int getChildCount() {
        return children != null ? children.size() : 0;
    }

    public boolean isLeaf() {
        return children == null || children.isEmpty();
    }

    // Métodos utilitarios adicionales
    public void clearChildren() {
        if (children != null) {
            children.clear();
        }
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    public TreeNode findNode(Object dataToFind) {
        if (Objects.equals(this.data, dataToFind)) {
            return this;
        }

        if (children != null) {
            for (TreeNode child : children) {
                TreeNode found = child.findNode(dataToFind);
                if (found != null) {
                    return found;
                }
            }
        }

        return null;
    }

    // Método para facilitar la construcción de árboles (similar a DefaultTreeNode)
    public static TreeNode createRootNode() {
        return new TreeNode();
    }

    public static TreeNode createNode(Object data) {
        return new TreeNode(data);
    }

    public static TreeNode createNode(Object data, String type) {
        return new TreeNode(data, type);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", type='" + type + '\'' +
                ", children=" + (children != null ? children.size() : 0) +
                ", expanded=" + expanded +
                ", selectable=" + selectable +
                '}';
    }
}
