package com.app.auxiliar.repositories;

import com.app.auxiliar.entities.EnrollmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnrollmentHistoryRepository extends JpaRepository<EnrollmentHistory, Integer> {}

