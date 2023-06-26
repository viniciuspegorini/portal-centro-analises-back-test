package com.portal.centro.API.service;

import com.portal.centro.API.enums.Type;
import com.portal.centro.API.exceptions.ValidationException;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.StudentTeacher;
import com.portal.centro.API.model.User;
import com.portal.centro.API.repository.StudentTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

@Service
public class StudentTeacherService extends GenericService<StudentTeacher, Long> {

    private final StudentTeacherRepository studentTeacherRepository;

    @Autowired
    public StudentTeacherService(
            StudentTeacherRepository studentTeacherRepository) {
        super(studentTeacherRepository);
        this.studentTeacherRepository = studentTeacherRepository;
    }

    @Override
    public StudentTeacher save(StudentTeacher requestBody) throws Exception {

        StudentTeacher studentTeacherDb = studentTeacherRepository.findByStudentIdAndAproved(requestBody.getStudent().getId(), true);
        if (studentTeacherDb != null) {
            throw new ValidationException("Este aluno já esta vinculado a um professor.");
        }

        requestBody.setCreatedAt(LocalDate.now());
        StudentTeacher studentTeacher = super.save(requestBody);

        return studentTeacher;
    }

    public List<User> listByTeacher(Long teacherId) {
        return studentTeacherRepository.listByTeacher(teacherId);
    }

    public User findByStudent(Long studentId) {
        return studentTeacherRepository.findByStudent(studentId);
    }

}
