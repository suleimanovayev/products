package com.internet.shop.products.service.impl;

import com.internet.shop.products.entity.Bucket;
import com.internet.shop.products.repository.BucketRepository;
import com.internet.shop.products.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements CrudService<Bucket> {
    private final BucketRepository bucketRepository;


    @Override
    public Bucket findById(Long entityId) throws EntityNotFoundException {
        return bucketRepository.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Not fount bucket with id: %s", entityId)));
    }

    @Override
    public Bucket save(Bucket entity) {
        return bucketRepository.save(entity);
    }

    @Override
    public Bucket update(Bucket entity) {
        return save(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        bucketRepository.deleteById(entityId);
    }
}
