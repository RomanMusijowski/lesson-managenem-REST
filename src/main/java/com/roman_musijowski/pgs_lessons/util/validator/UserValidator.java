package com.roman_musijowski.pgs_lessons.util.validator;

import com.roman_musijowski.pgs_lessons.util.dto.UserDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserDTO userDTO = (UserDTO) target;

        if(!userDTO.getPassword().equals(userDTO.getEncryptedPassword())){
            errors.rejectValue("password", "PasswordsDontMatch.customerForm.password", "Passwords Don't Match");
            errors.rejectValue("encryptedPassword", "PasswordsDontMatch.customerForm.encryptedPassword", "Passwords Don't Match");
        }

    }
}
