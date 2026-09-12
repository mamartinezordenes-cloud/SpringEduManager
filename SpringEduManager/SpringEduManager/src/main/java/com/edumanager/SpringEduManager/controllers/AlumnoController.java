package com.edumanager.SpringEduManager.controllers;

import com.edumanager.SpringEduManager.models.Alumno;
import com.edumanager.SpringEduManager.services.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    // 1. Listar todos los alumnos
    @GetMapping
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoService.listarAlumnos());
        return "alumnos/listar";
    }

    // 2. Mostrar formulario para registrar un nuevo alumno
    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "alumnos/formulario";
    }

    // 3. Guardar el alumno (con validaciones)
    @PostMapping("/guardar")
    public String guardarAlumno(@Valid @ModelAttribute("alumno") Alumno alumno, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "alumnos/formulario"; // Si hay errores, vuelve al formulario
        }
        alumnoService.guardarAlumno(alumno);
        return "redirect:/alumnos";
    }

    // 4. Mostrar formulario para editar un alumno existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Alumno alumno = alumnoService.obtenerAlumnoPorId(id);
        if (alumno == null) {
            return "redirect:/alumnos";
        }
        model.addAttribute("alumno", alumno);
        return "alumnos/formulario";
    }

    // 5. Eliminar un alumno
    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id) {
        alumnoService.eliminarAlumno(id);
        return "redirect:/alumnos";
    }
}


