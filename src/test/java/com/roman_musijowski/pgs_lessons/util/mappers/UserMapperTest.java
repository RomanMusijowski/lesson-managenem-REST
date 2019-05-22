package com.roman_musijowski.pgs_lessons.util.mappers;

import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.util.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserMapperTest {

    private static final String USERNAME = "userName@gmail.com";
    private static final long ID = 1L;

    UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    public void userToUserDTO() {
        //given
        User user = new User();
        user.setUserName(USERNAME);
        user.setId(ID);

        //when
        UserDTO userDTO= userMapper.userToUserDTO(user);

        //then
        assertEquals(Long.valueOf(ID), userDTO.getId());
        assertEquals(USERNAME, userDTO.getUserName());
    }

    @Test
    public void userDTOToUser() {
        //given
        UserDTO userDTO = new UserDTO();
        userDTO .setUserName(USERNAME);
        userDTO .setId(ID);

        //when
        User user = userMapper.userDTOToUser(userDTO);

        //then
        assertEquals(Long.valueOf(ID), user.getId());
        assertEquals(USERNAME, user.getUserName());
    }
}