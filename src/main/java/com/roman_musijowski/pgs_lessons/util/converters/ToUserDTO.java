package com.roman_musijowski.pgs_lessons.util.converters;

import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.util.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToUserDTO implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User source) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(source.getId());
        userDTO.setUserName(source.getUserName());
        userDTO.setName(source.getName());
        userDTO.setSurName(source.getSurName());
        userDTO.setFieldOfStudy(source.getFieldOfStudy());
        userDTO.setYearOfStudies(source.getYearOfStudies());
        userDTO.setIndex(source.getIndex());

        return userDTO;
    }
}
