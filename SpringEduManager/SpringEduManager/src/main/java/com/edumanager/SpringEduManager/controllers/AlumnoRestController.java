package com.edumanager.SpringEduManager.controllers;

import com.edumanager.SpringEduManager.models.Alumno;
import com.edumanager.SpringEduManager.services.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoRestController {

    @Autowired
    private AlumnoService alumnoService;

    // 1. GET: Listar todos los alumnos en formato JSON
    @GetMapping
    public List<Alumno> listarRest() {
        return alumnoService.listarAlumnos();
    }

    // 2. GET por ID: Buscar un alumno específico
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerPorId(@PathVariable Long id) {
        Alumno alumno = alumnoService.obtenerAlumnoPorId(id);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno);
    }

    // 3. POST: Crear un nuevo alumno
    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@Valid @RequestBody Alumno alumno) {
        Alumno nuevoAlumno = alumnoService.guardarAlumno(alumno);
        return ResponseEntity.ok(nuevoAlumno);
    }

    // 4. PUT: Actualizar un alumno existente
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Long id, @Valid @RequestBody Alumno alumnoDetalles) {
        Alumno alumnoExistente = alumnoService.obtenerAlumnoPorId(id);
        if (alumnoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        
        alumnoExistente.setNombre(alumnoDetalles.getNombre());
        alumnoExistente.setApellidoPaterno(alumnoDetalles.getApellidoPaterno());
        alumnoExistente.setApellidoMaterno(alumnoDetalles.getApellidoMaterno());
        alumnoExistente.setFechaNacimiento(alumnoDetalles.getFechaNacimiento());
        alumnoExistente.setDireccion(alumnoDetalles.getDireccion());
        alumnoExistente.setComuna(alumnoDetalles.getComuna());
        alumnoExistente.setCarrera(alumnoDetalles.getCarrera());
        alumnoExistente.setEmail(alumnoDetalles.getEmail());
        alumnoExistente.setFechaIngreso(alumnoDetalles.getFechaIngreso());
        alumnoExistente.setTelefono(alumnoDetalles.getTelefono());
        alumnoExistente.setMatriculado(alumnoDetalles.getMatriculado());

        Alumno actualizado = alumnoService.guardarAlumno(alumnoExistente);
        return ResponseEntity.ok(actualizado);
    }

    // 5. DELETE: Eliminar un alumno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        Alumno alumno = alumnoService.obtenerAlumnoPorId(id);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        alumnoService.eliminarAlumno(id);
        return ResponseEntity.noContent().build();
    }
}