package com.app.auxiliar.repositories;

import com.app.auxiliar.entities.DeanInfo;
import com.app.auxiliar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IDeanInfoRepository extends JpaRepository<DeanInfo, Integer> {
    Optional<DeanInfo> findByUser(User user);
}


