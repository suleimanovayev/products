package com.internet.shop.products.tree;

import com.internet.shop.products.function.FuncUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class Treenode<T, N extends Treenode<T, N>> {
    protected N parent;
    protected List<N> children;
    protected T data;

    public Treenode(N parent, List<N> children, T data) {
        this.parent = parent;
        this.children = children;
        this.data = data;
    }

    public Treenode() {
    }

    public static <T, N extends Treenode<T, N>> N makeTree(List<T> dates, TypeAdapter<T, N> typeAdapter) {

        N root = typeAdapter.newInstance();
        root.children = new ArrayList<>();

        for (T top : FuncUtils.filter(dates, typeAdapter::inTopLevel)) {
            root.children.add(extractNode(top, root, dates, typeAdapter));
        }
        return root;
    }

    protected static <T, N extends Treenode<T, N>> N extractNode(T data, N parent, List<T> datas, TypeAdapter<T, N> typeAdapter) {
        N node = typeAdapter.newInstance();
        node.data = data;
        node.parent = parent;

        List<T> children = FuncUtils.filter(datas, d -> typeAdapter.isChildOf(data, d));

        if (!children.isEmpty()) {
            node.children = new ArrayList<>();
            for (T dat : children) {
                node.children.add(extractNode(dat, node, datas, typeAdapter));
            }
        }
        return node;
    }
}
