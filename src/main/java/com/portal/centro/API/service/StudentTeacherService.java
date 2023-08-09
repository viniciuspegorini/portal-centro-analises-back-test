package com.portal.centro.API.service;

import com.portal.centro.API.enums.Type;
import com.portal.centro.API.exceptions.ValidationException;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.StudentTeacher;
import com.portal.centro.API.model.User;
import com.portal.centro.API.repository.StudentTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentTeacherService extends GenericService<StudentTeacher, Long> {

    private final StudentTeacherRepository studentTeacherRepository;
    private final UserService userService;

    @Autowired
    public StudentTeacherService(
            StudentTeacherRepository studentTeacherRepository, UserService userService) {
        super(studentTeacherRepository);
        this.userService = userService;
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

    public List<StudentTeacher> listByTeacher(Long teacherId) {
        return studentTeacherRepository.listByTeacherWhere(teacherId);
    }

    public List<User> listStudentsByTeacher(Long teacherId) {
        return studentTeacherRepository.listStudentsByTeacher(teacherId);
    }
    public List<StudentTeacher> findByStudent(Long studentId) {
        return studentTeacherRepository.findByStudentWhere(studentId);
    }

    public List<StudentTeacher> getAllByUser() {
        User user = userService.findSelfUser();

        if(user.getRole() == Type.PROFESSOR) {
            return studentTeacherRepository.listByTeacherWhere(user.getId());
        } else {
            return studentTeacherRepository.findByStudentWhere(user.getId());
        }
    }

}
