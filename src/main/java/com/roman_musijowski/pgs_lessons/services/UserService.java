package com.roman_musijowski.pgs_lessons.services;

import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.util.dto.UserDTO;

public interface UserService extends CRUDService<UserDTO> {

        UserDTO saveAndReturnDTO(User user);
        User findByUserName(String userName);
}
