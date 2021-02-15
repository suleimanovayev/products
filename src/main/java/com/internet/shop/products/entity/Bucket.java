package com.internet.shop.products.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "BUCKETS")
public class Bucket extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "BUCKET_PRODUCT", joinColumns = @JoinColumn(name = "BUCKET_ID"),
//            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
//    private List<Product> productList;
}
