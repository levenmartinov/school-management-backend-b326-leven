package com.techproed.schoolmanagementbackendb326.repository.business;


import com.techproed.schoolmanagementbackendb326.entity.concretes.business.StudentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long> {


    @Query("select (count (s)>0) from StudentInfo s where s.student.id = ?1 and s.lesson.lessonName = ?2")
    boolean isStudentInfoExistForLesson(Long studentId, String lessonName);

    Page<StudentInfo> findAllByStudent_Id(Long id, Pageable pageable);

    List<StudentInfo> findByStudent_Id(
            Long studentİd);

    Page<StudentInfo> findAllByTeacher_Id(Long id, Pageable pageable);


}
