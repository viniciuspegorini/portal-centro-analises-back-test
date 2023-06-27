package com.portal.centro.API.controller;

import com.portal.centro.API.generic.crud.GenericController;
import com.portal.centro.API.model.StudentTeacher;
import com.portal.centro.API.model.User;
import com.portal.centro.API.service.StudentTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student-teacher")
public class StudentTeacherController extends GenericController<StudentTeacher, Long> {

    private final StudentTeacherService studentTeacherService;

    @Autowired
    public StudentTeacherController(StudentTeacherService studentTeacherService) {
        super(studentTeacherService);
        this.studentTeacherService = studentTeacherService;
    }

    // busca a lista de alunos pelo id do professor
    @GetMapping(path = "/listByTeacher/{idProfessor}")
    public ResponseEntity<List<User>> listByTeacher(@PathVariable Long idProfessor){
        return ResponseEntity.ok(studentTeacherService.listByTeacher(idProfessor));
    }

    // busca o professor pelo id do aluno
    @GetMapping(path = "/findByStudent/{idAluno}")
    public ResponseEntity<User> findByStudent(@PathVariable Long idAluno){
        return ResponseEntity.ok(studentTeacherService.findByStudent(idAluno));
    }
}
