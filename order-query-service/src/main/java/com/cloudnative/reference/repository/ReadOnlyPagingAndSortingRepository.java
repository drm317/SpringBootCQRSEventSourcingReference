package com.cloudnative.reference.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.cloudnative.reference.domain.AnOrder;

@NoRepositoryBean
public interface ReadOnlyPagingAndSortingRepository extends PagingAndSortingRepository<AnOrder, String> {

	@Override
    @SuppressWarnings("unchecked")
    @RestResource(exported = false)
	AnOrder save(AnOrder entity);

    @Override
    @RestResource(exported = false)
    void delete(String aLong);

    @Override
    @RestResource(exported = false)
    void delete(AnOrder entity);
}
