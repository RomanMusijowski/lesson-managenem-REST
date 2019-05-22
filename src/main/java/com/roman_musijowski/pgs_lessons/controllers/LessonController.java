package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;
import com.roman_musijowski.pgs_lessons.util.dto.LessonListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(LessonController.BASE_URL)
public class LessonController {

    public static final String BASE_URL = "/lessons";

    private static final Logger logger = LoggerFactory.getLogger(LessonController.class);

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

//    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public LessonListDTO getListOfLessons() {
        return new LessonListDTO(lessonService.listAll());
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public LessonDTO getLessonById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonDTO createNewLesson(@RequestBody LessonDTO lessonDTO) {
        return lessonService.createNew(lessonDTO);
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public LessonDTO updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        return lessonService.putByDTO(id, lessonDTO);
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public LessonDTO patchLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        return lessonService.patchByDTO(id, lessonDTO);
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteById(id);
    }
}