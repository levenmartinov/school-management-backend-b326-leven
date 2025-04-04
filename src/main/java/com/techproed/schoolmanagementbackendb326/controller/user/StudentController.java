package com.techproed.schoolmanagementbackendb326.controller.user;

import com.techproed.schoolmanagementbackendb326.payload.request.business.AddLessonProgramForStudent;
import com.techproed.schoolmanagementbackendb326.payload.request.user.StudentRequest;
import com.techproed.schoolmanagementbackendb326.payload.request.user.StudentUpdateRequest;
import com.techproed.schoolmanagementbackendb326.payload.response.business.ResponseMessage;
import com.techproed.schoolmanagementbackendb326.payload.response.user.StudentResponse;
import com.techproed.schoolmanagementbackendb326.service.user.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/save")
    public ResponseMessage<StudentResponse> save(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    @PreAuthorize("hasAnyAuthority('Student')")
    @PutMapping("/update")
    public ResponseEntity<String> updateStudent(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid StudentUpdateRequest studentUpdateRequest) {
        return ResponseEntity.ok(studentService.updateStudent(httpServletRequest, studentUpdateRequest));
    }


    @PreAuthorize("hasAnyAuthority('Admin')")
    @PutMapping("/updateByAdmin/{studentId}")
    public ResponseMessage<StudentResponse> updateStudentByManager(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentRequest studentRequest) {
        return studentService.updateStudentByManager(studentId, studentRequest);
    }

    @PreAuthorize("hasAnyAuthority('Admin','Dean','ViceDean')")
    @GetMapping("/changeStatus")
    public ResponseMessage changeStatus(@RequestParam Long id,
                                        @RequestParam boolean status) {
        return studentService.changeStatus(id, status);

    }


    public ResponseMessage<StudentResponse> addLessonProgram(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid AddLessonProgramForStudent addLessonProgramForStudent) {
        return studentService.addLessonProgram(httpServletRequest, addLessonProgramForStudent);
    }


}
