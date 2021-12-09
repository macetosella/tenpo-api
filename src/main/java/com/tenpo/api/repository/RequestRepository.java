package com.tenpo.api.repository;

import com.tenpo.api.model.RequestLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends PagingAndSortingRepository<RequestLogger, Long> {

    Page<RequestLogger> findAll(Pageable pageable);

}
