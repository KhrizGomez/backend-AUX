package com.app.auxiliar.repositories;

import com.app.auxiliar.entities.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICareerRepository extends JpaRepository<Career, Integer> {}

