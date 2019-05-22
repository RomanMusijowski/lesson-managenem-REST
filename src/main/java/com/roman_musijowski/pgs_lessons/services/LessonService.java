package com.roman_musijowski.pgs_lessons.services;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;

public interface LessonService extends CRUDService<LessonDTO> {

    LessonDTO saveAndReturnDTO(Lesson lesson);
}
