package com.app.auxiliar.repositories;

import com.app.auxiliar.entities.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISemesterRepository extends JpaRepository<Semester, Integer> {}

