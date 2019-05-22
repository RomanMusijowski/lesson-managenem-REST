package com.roman_musijowski.pgs_lessons.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractRestControllerTest {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
