package com.portal.centro.API.controller;

import com.portal.centro.API.generic.crud.GenericController;
import com.portal.centro.API.model.StudentTeacher;
import com.portal.centro.API.model.User;
import com.portal.centro.API.service.StudentTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/listByTeacher")
    public ResponseEntity<List<User>> listByTeacher(@RequestBody User requestBody){
        return ResponseEntity.ok(studentTeacherService.listByTeacher(requestBody.getId()));
    }

    @GetMapping(path = "/findByStudent")
    public ResponseEntity<User> findByStudent(@RequestBody User requestBody){
        return ResponseEntity.ok(studentTeacherService.findByStudent(requestBody.getId()));
    }
}
