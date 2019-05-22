package com.roman_musijowski.pgs_lessons.util.mappers;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-19T17:15:19+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class LessonMapperImpl implements LessonMapper {

    @Override
    public LessonDTO lessoneToLessonDTO(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonDTO lessonDTO = new LessonDTO();

        lessonDTO.setLessonId( lesson.getLessonId() );
        lessonDTO.setTitle( lesson.getTitle() );
        lessonDTO.setDescription( lesson.getDescription() );
        lessonDTO.setTeacherInfo( lesson.getTeacherInfo() );
        lessonDTO.setDate( lesson.getDate() );

        return lessonDTO;
    }

    @Override
    public Lesson lessoneDTOToLesson(LessonDTO lessonDTO) {
        if ( lessonDTO == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setDate( lessonDTO.getDate() );
        lesson.setLessonId( lessonDTO.getLessonId() );
        lesson.setTitle( lessonDTO.getTitle() );
        lesson.setDescription( lessonDTO.getDescription() );
        lesson.setTeacherInfo( lessonDTO.getTeacherInfo() );

        return lesson;
    }
}
