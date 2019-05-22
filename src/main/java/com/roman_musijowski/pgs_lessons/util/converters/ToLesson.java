package com.roman_musijowski.pgs_lessons.util.converters;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToLesson implements Converter<LessonDTO, Lesson> {

    @Override
    public Lesson convert(LessonDTO source) {
        Lesson lesson = new Lesson();

        lesson.setLessonId(source.getLessonId());
        lesson.setTitle(source.getTitle());
        lesson.setDescription(source.getDescription());
        lesson.setTeacherInfo(source.getTeacherInfo());
        lesson.setDate(source.getDate());

        return lesson;
    }
}
