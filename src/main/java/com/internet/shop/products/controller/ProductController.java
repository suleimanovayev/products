package com.internet.shop.products.controller;

import com.internet.shop.products.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
@RequiredArgsConstructor
@Getter
@Setter
public class ProductController implements Serializable {
    private TreeNode root;
    private TreeNode selectedProduct;
    private final ProductService productService;

    @PostConstruct
    public void init() {
        root = productService.findRoot();
    }
}