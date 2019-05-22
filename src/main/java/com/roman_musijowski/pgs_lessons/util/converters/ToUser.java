package com.roman_musijowski.pgs_lessons.util.converters;

import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.util.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToUser implements Converter<UserDTO, User> {

    @Override
    public User convert(UserDTO source) {
        User user = new User();

        user.setId(source.getId());
        user.setUserName(source.getUserName());
        user.setName(source.getName());
        user.setSurName(source.getSurName());
        user.setFieldOfStudy(source.getFieldOfStudy());
        user.setYearOfStudies(source.getYearOfStudies());
        user.setPassword(source.getPassword());
        user.setIndex(source.getIndex());

        return user;
    }
}
