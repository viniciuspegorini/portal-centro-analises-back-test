package com.portal.centro.API.repository;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.model.StudentTeacher;
import com.portal.centro.API.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentTeacherRepository extends GenericRepository<StudentTeacher, Long> {

    @Query(value = "Select st.student From student_teacher as st where st.teacher_id=:teacherId")
    List<User> listByTeacher(Long teacherId);

    @Query(value = "Select st.teacher From student_teacher as st where st.student_id=:studentId")
    User findByStudent(Long studentId);

    StudentTeacher findByStudentIdAndAproved(Long studentId, Boolean aproved);

}
