package com.roman_musijowski.pgs_lessons.controllers;

import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LessonControllerTest extends AbstractRestControllerTest {

    public static final String NAME = "JAVA";

    @Mock
    LessonService lessonService;

    @InjectMocks
    LessonController lessonController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(lessonController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getListOfLessons() throws Exception {
        //given
        LessonDTO lesson1 = new LessonDTO();
        lesson1.setTitle("JAVA Spring");
        lesson1.setTeacherInfo("Michale Weston");

        LessonDTO lesson2 = new LessonDTO();
        lesson2.setTitle("Spring boot");
        lesson2.setTeacherInfo("Sam Axe");

        when(lessonService.listAll()).thenReturn(Arrays.asList(lesson1, lesson2));

        mockMvc.perform(get(LessonController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lessons", hasSize(2)));
    }

    @Test
    public void getLessonById() throws Exception {
        LessonDTO lesson1 = new LessonDTO();
        lesson1.setTitle("JAVA Spring");
        lesson1.setTeacherInfo("Michale Weston");

        when(lessonService.getById(anyLong())).thenReturn(lesson1);

        //when
        mockMvc.perform(get(LessonController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("JAVA Spring")));
    }

    @Test
    public void createNewLesson() throws Exception {
        //given
        LessonDTO lesson1 = new LessonDTO();
        lesson1.setTitle("JAVA Spring");
        lesson1.setTeacherInfo("Michale Weston");

        LessonDTO returnDTO = new LessonDTO();
        returnDTO.setTitle(lesson1.getTitle());
        returnDTO.setTeacherInfo(lesson1.getTeacherInfo());

        when(lessonService.createNew(any(LessonDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(post(LessonController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(lesson1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", equalTo("JAVA Spring")));
    }

    @Test
    public void updateLesson() throws Exception {
        //given
        LessonDTO lesson = new LessonDTO();
        lesson.setTitle("JAVA Spring");
        lesson.setTeacherInfo("Michale Weston");

        LessonDTO returnDTO = new LessonDTO();
        returnDTO.setTitle(lesson.getTitle());
        returnDTO.setTeacherInfo(lesson.getTeacherInfo());

        when(lessonService.putByDTO(anyLong(), any(LessonDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put(LessonController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(lesson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("JAVA Spring")))
                .andExpect(jsonPath("$.teacherInfo", equalTo("Michale Weston")));
    }

    @Test
    public void patchLesson() throws Exception {
        //given
        LessonDTO lesson = new LessonDTO();
        lesson.setTitle("JAVA Spring");

        LessonDTO returnDTO = new LessonDTO();
        returnDTO.setTitle(lesson.getTitle());
        returnDTO.setTeacherInfo("New TeacherINfo");

        when(lessonService.patchByDTO(anyLong(), any(LessonDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(LessonController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(lesson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("JAVA Spring")))
                .andExpect(jsonPath("$.teacherInfo", equalTo("New TeacherINfo")));
    }

    @Test
    public void deleteLesson() throws Exception {
        mockMvc.perform(delete(LessonController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(lessonService).deleteById(anyLong());
    }
}