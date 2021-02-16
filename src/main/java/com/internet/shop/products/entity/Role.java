package com.internet.shop.products.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;


@EqualsAndHashCode(callSuper = true, exclude = "users")
@Data
@Entity
@Table(name = "ROLES")
@NoArgsConstructor
public class Role extends BaseEntity {

    @Column(name = "ROLE_NAME")
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<User> users;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
