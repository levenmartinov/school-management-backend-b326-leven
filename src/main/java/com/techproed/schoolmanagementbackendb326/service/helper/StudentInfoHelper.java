package com.techproed.schoolmanagementbackendb326.service.helper;


import com.techproed.schoolmanagementbackendb326.entity.concretes.business.EducationTerm;
import com.techproed.schoolmanagementbackendb326.entity.concretes.business.Lesson;
import com.techproed.schoolmanagementbackendb326.entity.concretes.business.StudentInfo;
import com.techproed.schoolmanagementbackendb326.entity.concretes.user.User;
import com.techproed.schoolmanagementbackendb326.entity.enums.Note;
import com.techproed.schoolmanagementbackendb326.entity.enums.RoleType;
import com.techproed.schoolmanagementbackendb326.exception.ConflictException;
import com.techproed.schoolmanagementbackendb326.exception.ResourceNotFoundException;
import com.techproed.schoolmanagementbackendb326.payload.mappers.StudentInfoMapper;
import com.techproed.schoolmanagementbackendb326.payload.messages.ErrorMessages;
import com.techproed.schoolmanagementbackendb326.payload.messages.SuccessMessages;
import com.techproed.schoolmanagementbackendb326.payload.request.business.StudentInfoRequest;
import com.techproed.schoolmanagementbackendb326.payload.response.business.ResponseMessage;
import com.techproed.schoolmanagementbackendb326.payload.response.business.StudentInfoResponse;
import com.techproed.schoolmanagementbackendb326.repository.business.StudentInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentInfoHelper {

    @Value("${final.exam.impact.percentage}")
    private Double finalExamPercentage;

    @Value("${midterm.exam.impact.percentage}")
    public Double midTermExamPercentage;

    private final StudentInfoRepository studentInfoRepository;

    public void validateLessonDuplication(Long studentId, String lessonName) {
        if (studentInfoRepository.isStudentInfoExistForLesson(studentId, lessonName)) {
            throw new ConflictException(String.format(ErrorMessages.ALREADY_CREATED_STUDENT_INFO_FOR_LESSON, lessonName));
        }
    }

    public Double calculateAverageScore(Double midtermExam, Double finalExam) {
        return (midtermExam * midTermExamPercentage) + (finalExam * finalExamPercentage);
    }

    public Note checkLetterGrade(Double average) {
        if (average < 50.0) {
            return Note.FF;
        } else if (average < 60) {
            return Note.DD;
        } else if (average < 65) {
            return Note.CC;
        } else if (average < 70) {
            return Note.CB;
        } else if (average < 75) {
            return Note.BB;
        } else if (average < 80) {
            return Note.BA;
        } else {
            return Note.AA;
        }
    }


    public StudentInfo isStudentInfoExistById(Long id) {
        return studentInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.STUDENT_INFO_NOT_FOUND, id)));
    }


}
