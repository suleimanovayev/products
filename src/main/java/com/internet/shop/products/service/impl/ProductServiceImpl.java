package com.internet.shop.products.service.impl;

import com.internet.shop.products.entity.Product;
import com.internet.shop.products.repository.ProductRepository;
import com.internet.shop.products.service.ProductService;
import com.internet.shop.products.tree.ProductData;
import com.internet.shop.products.tree.Treenode;
import com.internet.shop.products.tree.TypeAdapter;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long entityId) throws EntityNotFoundException {
        return productRepository.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Not fount product with id: %s", entityId)));
    }

    @Override
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product update(Product entity) {
        return save(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        productRepository.deleteById(entityId);
    }

    @Override
    public TreeNode findRoot() {
        List<Product> productList = productRepository.findAll();

        ProductData root = Treenode.makeTree(productList, new TypeAdapter<Product, ProductData>() {
            @Override
            public ProductData newInstance() {
                return new ProductData();
            }

            @Override
            public boolean isChildOf(Product parentNode, Product child) {
                return child.getParentId().equals(parentNode.getId());
            }

            @Override
            public boolean inTopLevel(Product item) {
                return item.getParentId() == 0;
            }
        });
        return createProducts(new DefaultTreeNode(root.getData(), null), root.getChildren());

    }

    private TreeNode createProducts(DefaultTreeNode parent, List<ProductData> children) {
        if (children == null) {
            return parent;
        }

        for (ProductData productData : children) {
            DefaultTreeNode node = new DefaultTreeNode(productData.getData(), parent);
            createProducts(node, productData.getChildren());
        }
        return parent;
    }
}
