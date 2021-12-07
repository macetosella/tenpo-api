package com.tenpo.repository;

import com.tenpo.model.UserData;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, Long> {

    Optional<UserData> findByUserNickNameAndPassword(String userNickName, String password);

}
