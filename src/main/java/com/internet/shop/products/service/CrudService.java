package com.internet.shop.products.service;

import com.internet.shop.products.entity.BaseEntity;

import javax.persistence.EntityNotFoundException;

public interface CrudService<T extends BaseEntity> {
    T findById(Long entityId) throws EntityNotFoundException;

    T save(T entity);

    T update(T entity);

    void deleteById(Long entityId);
}
