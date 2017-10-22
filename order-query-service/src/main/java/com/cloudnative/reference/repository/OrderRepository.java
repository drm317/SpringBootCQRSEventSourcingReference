package com.cloudnative.reference.repository;

import com.cloudnative.reference.domain.AnOrder;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SuppressWarnings("unchecked")
@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends ReadOnlyPagingAndSortingRepository {
  public List<AnOrder> findByDescription(@Param("description") String description);
}