package com.tenpo.api.repository;

import com.tenpo.api.model.UserData;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, Long> {

    Optional<UserData> findByUserName(String userName);
}
