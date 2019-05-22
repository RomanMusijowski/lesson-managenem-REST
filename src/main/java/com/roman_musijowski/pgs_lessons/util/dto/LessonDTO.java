package com.roman_musijowski.pgs_lessons.util.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {

    private Long lessonId;

    @NotEmpty
    @Size(min = 5, max = 50)
    private String title;
    @NotEmpty
    @Size(min = 10, max = 200)
    private String description;
    @NotEmpty
    @Size(min = 10, max = 50)
    private String teacherInfo;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;

    @JsonProperty("lesson_url")
    private String lessonUrl;

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(String teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLessonUrl() {
        return lessonUrl;
    }

    public void setLessonUrl(String lessonUrl) {
        this.lessonUrl = lessonUrl;
    }
}
