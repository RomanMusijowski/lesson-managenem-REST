package com.roman_musijowski.pgs_lessons.util.converters;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToLessonDTO implements Converter<Lesson, LessonDTO> {
    @Override
    public LessonDTO convert(Lesson source) {
        LessonDTO lessonDTO = new LessonDTO();

        lessonDTO.setLessonId(source.getLessonId());
        lessonDTO.setTitle(source.getTitle());
        lessonDTO.setDescription(source.getDescription());
        lessonDTO.setTeacherInfo(source.getTeacherInfo());
        lessonDTO.setDate(source.getDate());

        return lessonDTO;
    }
}
