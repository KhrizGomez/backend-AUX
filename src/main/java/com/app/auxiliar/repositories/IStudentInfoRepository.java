package com.app.auxiliar.repositories;

import com.app.auxiliar.entities.StudentInfo;
import com.app.auxiliar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IStudentInfoRepository extends JpaRepository<StudentInfo, Integer> {
    Optional<StudentInfo> findByUser(User user);
}


