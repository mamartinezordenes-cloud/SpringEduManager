package com.edumanager.SpringEduManager.repositories;

import com.edumanager.SpringEduManager.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    
    List<Alumno> findByCarrera(String carrera);
    List<Alumno> findByComuna(String comuna);
}
