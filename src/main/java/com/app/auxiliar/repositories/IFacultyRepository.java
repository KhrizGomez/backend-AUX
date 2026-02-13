package com.app.auxiliar.repositories;

import com.app.auxiliar.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacultyRepository extends JpaRepository<Faculty, Integer> {}
