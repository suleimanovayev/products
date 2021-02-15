package com.internet.shop.products.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FuncUtils {

    public static <X> List<X> filter(List<X> list, Function<X, Boolean> function) {
        List<X> selected = new ArrayList<>();
        for (X x : list) {
            if (function.apply(x)) {
                selected.add(x);
            }
        }
        return selected;
    }
}
