package com.internet.shop.products.service;

import com.internet.shop.products.entity.Product;
import org.primefaces.model.TreeNode;

public interface ProductService extends CrudService<Product>{

    TreeNode findRoot();
}
