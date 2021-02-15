package com.internet.shop.products.service.impl;

import com.internet.shop.products.entity.Product;
import com.internet.shop.products.repository.ProductRepository;
import com.internet.shop.products.service.ProductService;
import com.internet.shop.products.tree.Treenode;
import com.internet.shop.products.tree.TypeAdapter;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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


    private List<Product> cj(List<Product> productList) {
        return productList.stream().limit(10).collect(Collectors.toList());
    }

//    public DefaultTreeNode makeTree(ProductData root) {
//        DefaultTreeNode rootNode = new DefaultTreeNode(root);
//        for (ProductData top : root.getChildren()) {
//            new DefaultTreeNode(extractNode(top), rootNode);
////            root.children.add(extractNode(top, root, dates, typeAdapter));
//        }
//        return rootNode;
//    }
//
//    protected  DefaultTreeNode extractNode(ProductData data) {
//        DefaultTreeNode root = new DefaultTreeNode(data);
//
//        if (!data.getChildren().isEmpty()) {
//            for (ProductData dat : data.getChildren()) {
//                new DefaultTreeNode(root, extractNode(data));
//                extractNode(dat);
////                node.children.add(extractNode(dat, node, datas, typeAdapter));
//            }
//        }
//        return root;
//    }


    public TreeNode createDocuments() {
        TreeNode root = new DefaultTreeNode(new Product("Files"), null);

        TreeNode applications = new DefaultTreeNode(new Product("Applications"), root);
        TreeNode cloud = new DefaultTreeNode(new Product("Cloud"), root);
        TreeNode desktop = new DefaultTreeNode(new Product("Desktop"), root);
        TreeNode documents = new DefaultTreeNode(new Product("Documents"), desktop);
        TreeNode downloads = new DefaultTreeNode(new Product("Downloads"), desktop);
        TreeNode main = new DefaultTreeNode(new Product("Main"), documents);
        TreeNode other = new DefaultTreeNode(new Product("Other"), documents);
        TreeNode pictures = new DefaultTreeNode(new Product("Pictures"), root);
        TreeNode videos = new DefaultTreeNode(new Product("Videos"), root);
        TreeNode primeface = new DefaultTreeNode(new Product("Primefaces"), applications);
        TreeNode primefacesapp = new DefaultTreeNode("app", new Product("primefaces.app"), primeface);
        TreeNode nativeapp = new DefaultTreeNode("app", new Product("native.app"), primeface);
        TreeNode mobileapp = new DefaultTreeNode("app", new Product("mobile.app"), primeface);
        TreeNode editorapp = new DefaultTreeNode("app", new Product("editor.app"), applications);
        TreeNode settingsapp = new DefaultTreeNode("app", new Product("settings.app"), applications);

        return root;
    }

    public TreeNode createCheckboxDocuments() {
        TreeNode root = new CheckboxTreeNode(new Product("Files"), null);

        TreeNode applications = new CheckboxTreeNode(new Product("Applications"), root);
        TreeNode cloud = new CheckboxTreeNode(new Product("Cloud"), root);
        TreeNode desktop = new CheckboxTreeNode(new Product("Desktop"), root);
        TreeNode documents = new CheckboxTreeNode(new Product("Documents"), root);
        TreeNode downloads = new CheckboxTreeNode(new Product("Downloads"), root);
        TreeNode main = new CheckboxTreeNode(new Product("Main"), root);
        TreeNode other = new CheckboxTreeNode(new Product("Other"), root);
        TreeNode pictures = new CheckboxTreeNode(new Product("Pictures"), root);
        TreeNode videos = new CheckboxTreeNode(new Product("Videos"), root);

        //Applications
        TreeNode primeface = new CheckboxTreeNode(new Product("Primefaces"), applications);
        TreeNode primefacesapp = new CheckboxTreeNode("app", new Product("primefaces.app"), primeface);
        TreeNode nativeapp = new CheckboxTreeNode("app", new Product("native.app"), primeface);
        TreeNode mobileapp = new CheckboxTreeNode("app", new Product("mobile.app"), primeface);
        TreeNode editorapp = new CheckboxTreeNode("app", new Product("editor.app"), applications);
        TreeNode settingsapp = new CheckboxTreeNode("app", new Product("settings.app"), applications);
        return root;
    }
}