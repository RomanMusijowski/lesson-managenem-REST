package com.roman_musijowski.pgs_lessons.util.mappers;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);
    LessonDTO lessoneToLessonDTO(Lesson lesson);
    Lesson lessoneDTOToLesson(LessonDTO lessonDTO);
}
