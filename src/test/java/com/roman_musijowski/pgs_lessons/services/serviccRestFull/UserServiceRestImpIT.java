package com.roman_musijowski.pgs_lessons.services.serviccRestFull;

import com.roman_musijowski.pgs_lessons.bootstrap.BootstrapH2Test;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.repositories.LessonRepositoryImp;
import com.roman_musijowski.pgs_lessons.repositories.RoleRepositoryImp;
import com.roman_musijowski.pgs_lessons.repositories.UserRepositoryImp;
import com.roman_musijowski.pgs_lessons.security.services.EncryptionService;
import com.roman_musijowski.pgs_lessons.security.services.EncryptionServiceImpl;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.RoleService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import com.roman_musijowski.pgs_lessons.util.dto.UserDTO;
import com.roman_musijowski.pgs_lessons.util.mappers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceRestImpIT {

    @Autowired
    UserRepositoryImp userRepositoryImp;
    @Autowired
    LessonRepositoryImp lessonRepositoryImp;
    @Autowired
    RoleRepositoryImp roleRepositoryImp;



    PasswordEncoder passwordEncoder;
    EncryptionService encryptionService;

    RoleService roleService;
    UserService userService;
    LessonService lessonService;

    RoleMapper roleMapper;
    LessonMapper lessonMapper;
    UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading User Data");
        System.out.println(userRepositoryImp.findAll().size());

        roleMapper = new RoleMapperImpl();
        lessonMapper = new LessonMapperImpl();
        userMapper = new UserMapperImpl();

        roleService = new RoleServiceRestImp(roleRepositoryImp, RoleMapper.INSTANCE);
        passwordEncoder = new BCryptPasswordEncoder();
        encryptionService = new EncryptionServiceImpl(passwordEncoder);

        userService = new UserServiceRestImp(encryptionService, userRepositoryImp
                , roleService, userMapper, roleMapper);
        lessonService = new LessonServiceRestImp(lessonRepositoryImp, LessonMapper.INSTANCE);

        //setup data for testing
        BootstrapH2Test bootstrap = new BootstrapH2Test(lessonService, roleService, userService, userMapper, roleMapper, lessonMapper);
        bootstrap.run(); //load data
    }

    @Test
    public void patchUserUpdateName(){
        String updatedName = "UpdatedName";
        long id = getUserIdValue();

        User originalUser = userRepositoryImp.getOne(id);
        assertNotNull(originalUser);
        //save original first name
        String originalFirstName = originalUser.getName();
        String originalLastName = originalUser.getSurName();

        UserDTO userDTO = new UserDTO();
        userDTO.setName(updatedName);

        userService.patchByDTO(id, userDTO);

        User updatedUser = userRepositoryImp.findById(id).get();

        assertNotNull(updatedUser);
        assertEquals(updatedName, updatedUser.getName());
        assertThat(originalFirstName, not(equalTo(updatedUser.getName())));
        assertThat(originalLastName, equalTo(updatedUser.getSurName()));
    }

    @Test
    public void patchUserUpdateLastName() throws Exception {
        String updatedName = "UpdatedName";
        long id = getUserIdValue();

        User originalUser = userRepositoryImp.getOne(id);
        assertNotNull(originalUser);

        //save original first/last name
        String originalName = originalUser.getName();
        String originalSurName = originalUser.getSurName();

        UserDTO userDTO = new UserDTO();
        userDTO.setSurName(updatedName);

        userService.patchByDTO(id, userDTO);

        User updatedUser = userRepositoryImp.findById(id).get();

        assertNotNull(updatedUser);
        assertEquals(updatedName, updatedUser.getSurName());
        assertThat(originalName, equalTo(updatedUser.getName()));
        assertThat(originalSurName, not(equalTo(updatedUser.getSurName())));
    }

    private Long getUserIdValue(){
        List<User> users = userRepositoryImp.findAll();

        System.out.println("Users Found: " + users.size());

        //return first id
        return users.get(0).getId();
    }
}