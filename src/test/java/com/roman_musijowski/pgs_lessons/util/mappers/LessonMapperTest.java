package com.roman_musijowski.pgs_lessons.util.mappers;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LessonMapperTest {

    public static final String TITLE = "JAVA";
    public static final long ID = 1L;

    LessonMapper lessonMapper = LessonMapper.INSTANCE;

    @Test
    public void lessoneToLessonDTO() {
        //given
        Lesson lesson = new Lesson();
        lesson.setTitle(TITLE);
        lesson.setLessonId(ID);

        //when
        LessonDTO lessonDTO = lessonMapper.lessoneToLessonDTO(lesson);

        //then
        assertEquals(Long.valueOf(ID), lessonDTO.getLessonId());
        assertEquals(TITLE, lessonDTO.getTitle());
    }

    @Test
    public void lessoneDTOToLesson() {
        //given
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setTitle(TITLE);
        lessonDTO.setLessonId(ID);

        //when
        Lesson lesson = lessonMapper.lessoneDTOToLesson(lessonDTO);

        //then
        assertEquals(Long.valueOf(ID), lesson.getLessonId());
        assertEquals(TITLE, lesson.getTitle());
    }
}