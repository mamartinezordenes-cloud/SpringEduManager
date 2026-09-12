package com.edumanager.SpringEduManager.services;

import com.edumanager.SpringEduManager.models.Alumno;
import com.edumanager.SpringEduManager.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    // Listar todos los alumnos
    public List<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    // Guardar o actualizar alumno
    public Alumno guardarAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    // Obtener alumno por ID
    public Alumno obtenerAlumnoPorId(Long id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        return alumno.orElse(null);
    }

    // Eliminar alumno por ID
    public void eliminarAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }

    // Búsqueda personalizada 1 por Carrera
    public List<Alumno> buscarPorCarrera(String carrera) {
        return alumnoRepository.findByCarrera(carrera);
    }

    // Búsqueda personalizada 2 por Comuna
    public List<Alumno> buscarPorComuna(String comuna) {
        return alumnoRepository.findByComuna(comuna);
    }
}
