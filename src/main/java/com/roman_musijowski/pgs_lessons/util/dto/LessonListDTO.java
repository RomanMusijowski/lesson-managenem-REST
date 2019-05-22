package com.roman_musijowski.pgs_lessons.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonListDTO {
    List<LessonDTO> lessons;
}
