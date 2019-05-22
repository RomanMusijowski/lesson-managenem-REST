package com.roman_musijowski.pgs_lessons.util.validator;

import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

public class LessonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LessonDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        LessonDTO lessonDTO = (LessonDTO) target;

        if (!lessonDTO.getDate().isAfter(LocalDateTime.now())){
            errors.rejectValue("date", "TimeDontMatch.lessonForm.date", "Date Don't Match");
        }
    }
}
