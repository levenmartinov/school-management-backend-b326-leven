package com.techproed.schoolmanagementbackendb326.controller.business;

import com.techproed.schoolmanagementbackendb326.payload.request.business.LessonProgramRequest;
import com.techproed.schoolmanagementbackendb326.payload.response.business.LessonProgramResponse;
import com.techproed.schoolmanagementbackendb326.payload.response.business.ResponseMessage;
import com.techproed.schoolmanagementbackendb326.service.business.LessonProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lessonPrograms")
@RequiredArgsConstructor
public class LessonProgramController {

    private final LessonProgramService lessonProgramService;


    @PreAuthorize("hasAnyAuthority('Admin','Dean','ViceDean')")
    @PostMapping("/save")
    public ResponseMessage<LessonProgramResponse> saveLessonProgram(
            @RequestBody @Valid LessonProgramRequest lessonProgramRequest) {
        return lessonProgramService.saveLessonProgram(lessonProgramRequest);
    }

    @PreAuthorize("hasAnyAuthority('Admin','Dean','ViceDean')")
    @GetMapping("/getAll")
    public List<LessonProgramResponse> getAllLessonPrograms() {
        return lessonProgramService.getAllLessonPrograms();
    }


    @PreAuthorize("hasAnyAuthority('Admin','Dean','ViceDean','Teacher','Student')")
    @GetMapping("/getLessonProgram/{id}")
    public LessonProgramResponse getLessonProgramById(@PathVariable Long id) {
        return lessonProgramService.findById(id);

    }

    @PreAuthorize("hasAnyAuthority('Admin','Dean','ViceDean','Teacher','Student')")
    @GetMapping("/getAllUnassigned")
    public List<LessonProgramResponse> getAllUnassignedLessonPrograms() {
        return lessonProgramService.getAllUnassigned();
    }

    @PreAuthorize("hasAnyAuthority('Admin','Dean','ViceDean','Teacher','Student')")
    @GetMapping("/getAllAssigned")
    public List<LessonProgramResponse> getAllAssignedLessonPrograms() {
        return lessonProgramService.getAllAssigned();
    }


    @PreAuthorize("hasAnyAuthority('Admin','Teacher')")
    @DeleteMapping("/delete/{id}")
    public ResponseMessage deleteLessonProgramById(@PathVariable Long id) {
        return lessonProgramService.deleteLessonProgramById(id);
    }

}

