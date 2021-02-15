package com.internet.shop.products.tree;

public interface TypeAdapter<T, N> {
    N newInstance();

    boolean isChildOf(T parentNode, T child);

    boolean inTopLevel(T item);
}
