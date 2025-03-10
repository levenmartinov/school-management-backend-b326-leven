package com.techproed.schoolmanagementbackendb326.service.user;

import com.techproed.schoolmanagementbackendb326.entity.concretes.business.LessonProgram;
import com.techproed.schoolmanagementbackendb326.entity.concretes.user.User;
import com.techproed.schoolmanagementbackendb326.entity.enums.RoleType;
import com.techproed.schoolmanagementbackendb326.payload.mappers.UserMapper;
import com.techproed.schoolmanagementbackendb326.payload.messages.SuccessMessages;
import com.techproed.schoolmanagementbackendb326.payload.request.user.TeacherRequest;
import com.techproed.schoolmanagementbackendb326.payload.response.business.ResponseMessage;
import com.techproed.schoolmanagementbackendb326.payload.response.user.UserResponse;
import com.techproed.schoolmanagementbackendb326.repository.user.UserRepository;
import com.techproed.schoolmanagementbackendb326.service.business.LessonProgramService;
import com.techproed.schoolmanagementbackendb326.service.helper.MethodHelper;
import com.techproed.schoolmanagementbackendb326.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final MethodHelper methodHelper;

    private final UniquePropertyValidator uniquePropertyValidator;

    private final LessonProgramService lessonProgramService;


    public ResponseMessage<UserResponse> saveTeacher(TeacherRequest teacherRequest) {
        List<LessonProgram> lessonProgramList =
                lessonProgramService.getLessonProgramById(teacherRequest.getLessonProgramList());
        //validate unique property
        uniquePropertyValidator.checkDuplication(
                teacherRequest.getUsername(),
                teacherRequest.getSsn(),
                teacherRequest.getPhoneNumber(),
                teacherRequest.getEmail());
        User teacher = userMapper.mapUserRequestToUser(teacherRequest, RoleType.TEACHER.getName());
        //set additional props.
        teacher.setIsAdvisor(teacherRequest.getIsAdvisoryTeacher());
        teacher.setLessonProgramList(lessonProgramList);
        User savedTeacher = userRepository.save(teacher);
        return ResponseMessage.<UserResponse>
                        builder()
                .message(SuccessMessages.TEACHER_SAVE)
                .httpStatus(HttpStatus.CREATED)
                .returnBody(userMapper.mapUserToUserResponse(savedTeacher))
                .build();
    }
}
