package com.app.auxiliar.repositories;

import com.app.auxiliar.entities.CoordinatorInfo;
import com.app.auxiliar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ICoordinatorInfoRepository extends JpaRepository<CoordinatorInfo, Integer> {
    Optional<CoordinatorInfo> findByUser(User user);
}


