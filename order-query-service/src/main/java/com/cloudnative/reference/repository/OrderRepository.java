package com.cloudnative.reference.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.cloudnative.reference.domain.Order;

@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface OrderRepository extends ReadOnlyPagingAndSortingRepository {
    public List<Order> findByDescription(@Param("description") String description);
}
