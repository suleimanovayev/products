package com.internet.shop.products.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "PRODUCTS")
public class Product extends BaseEntity implements Comparable<Product> {

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "PARENT_ID")
    private Long parentId;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "productList")
//    private List<Bucket> bucketList;
//

    public Product(String name) {
        this.name = name;
    }

    public Product() {
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.getName());
    }
}
