package com.tenpo.repository;

import com.tenpo.model.RequestLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends PagingAndSortingRepository<RequestLog, Long> {

    Page<RequestLog> findAll(Pageable pageable);

}
