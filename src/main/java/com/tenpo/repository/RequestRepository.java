package com.tenpo.repository;

import com.tenpo.model.RequestLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<RequestLog, Long> {

}
