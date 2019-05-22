package com.roman_musijowski.pgs_lessons.util.mappers;

import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.util.dto.UserDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-19T17:15:19+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUserName( user.getUserName() );
        userDTO.setName( user.getName() );
        userDTO.setSurName( user.getSurName() );
        userDTO.setYearOfStudies( user.getYearOfStudies() );
        userDTO.setFieldOfStudy( user.getFieldOfStudy() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setEncryptedPassword( user.getEncryptedPassword() );
        userDTO.setIndex( user.getIndex() );

        return userDTO;
    }

    @Override
    public User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( userDTO.getUserName() );
        user.setName( userDTO.getName() );
        user.setSurName( userDTO.getSurName() );
        user.setYearOfStudies( userDTO.getYearOfStudies() );
        user.setFieldOfStudy( userDTO.getFieldOfStudy() );
        user.setIndex( userDTO.getIndex() );
        user.setPassword( userDTO.getPassword() );
        user.setId( userDTO.getId() );
        user.setEncryptedPassword( userDTO.getEncryptedPassword() );

        return user;
    }
}
