package com.roman_musijowski.pgs_lessons.services.serviccRestFull;

import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.repositories.RoleRepositoryImp;
import com.roman_musijowski.pgs_lessons.repositories.UserRepositoryImp;
import com.roman_musijowski.pgs_lessons.security.services.EncryptionService;
import com.roman_musijowski.pgs_lessons.services.RoleService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import com.roman_musijowski.pgs_lessons.util.dto.RoleDTO;
import com.roman_musijowski.pgs_lessons.util.dto.UserDTO;
import com.roman_musijowski.pgs_lessons.util.mappers.RoleMapper;
import com.roman_musijowski.pgs_lessons.util.mappers.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceRestImpTest {

    @Mock
    UserRepositoryImp userRepositoryImp;
    @Mock
    RoleRepositoryImp roleRepositoryImp;

    UserMapper userMapper = UserMapper.INSTANCE;
    RoleMapper roleMapper = RoleMapper.INSTANCE;


    RoleService roleService;
    UserService userService;
    EncryptionService encryptionService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        roleService = new RoleServiceRestImp(roleRepositoryImp, RoleMapper.INSTANCE);

        userService = new UserServiceRestImp(encryptionService, userRepositoryImp
                , roleService, userMapper, roleMapper);
    }

    @Test
    public void findByUserName() {
        //given
        User user = new User();
        user.setId(1l);
        user.setUserName("userName");
        user.setName("Michale");
        user.setSurName("Weston");

        when(userRepositoryImp.findByUserName(anyString())).thenReturn(java.util.Optional.ofNullable(user));

        //when
        User userDTO = userService.findByUserName("userName");

        assertEquals("Michale", userDTO.getName());
    }

    @Test
    public void listAll() {
        //given
        User user1 = new User();
        user1.setId(1l);
        user1.setName("Michale");
        user1.setSurName("Weston");

        User user2 = new User();
        user2.setId(2l);
        user2.setName("Sam");
        user2.setSurName("Axe");

        when(userRepositoryImp.findAll()).thenReturn(Arrays.asList(user1, user2));

        //when
        List<UserDTO> customerDTOS = userService.listAll();

        //then
        assertEquals(2, customerDTOS.size());
    }

    @Test
    public void getById() {
        //given
        User user = new User();
        user.setId(1l);
        user.setName("Michale");
        user.setSurName("Weston");

        when(userRepositoryImp.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(user));

        //when
        UserDTO userDTO = userService.getById(1L);

        assertEquals("Michale", userDTO.getName());
    }

    @Test
    public void createNew() {
        loadRoles();
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Jim");

        User savedUser = new User();
        savedUser.setName(userDTO.getName());
        savedUser.setSurName(userDTO.getSurName());
        savedUser.setId(1l);

        when(userRepositoryImp.save(any(User.class))).thenReturn(savedUser);

        //when
        UserDTO savedDto = userService.createNew(userDTO);

        //then
        assertEquals(userDTO.getName(), savedDto.getName());
    }

    @Test
    public void putByDTO() {
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Jim");

        User savedUser = new User();
        savedUser.setName(userDTO.getName());
        savedUser.setSurName(userDTO.getSurName());
        savedUser.setId(1l);

        when(userRepositoryImp.save(any(User.class))).thenReturn(savedUser);

        //when
        UserDTO savedDto = userService.putByDTO(1L, userDTO);

        //then
        assertEquals(userDTO.getName(), savedDto.getName());
    }

    @Test
    public void deleteById() {
        Long id = 1L;

        userRepositoryImp.deleteById(id);

        verify(userRepositoryImp, times(1)).deleteById(anyLong());
    }

    public void loadRoles(){
        RoleDTO studen = new RoleDTO();
        studen.setRole("STUDENT");
        roleService.createNew(studen);
    }
}