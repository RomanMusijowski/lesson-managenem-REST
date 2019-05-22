package com.roman_musijowski.pgs_lessons.bootstrap;

import com.roman_musijowski.pgs_lessons.repositories.LessonRepositoryImp;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.RoleService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;
import com.roman_musijowski.pgs_lessons.util.dto.RoleDTO;
import com.roman_musijowski.pgs_lessons.util.dto.UserDTO;
import com.roman_musijowski.pgs_lessons.util.mappers.LessonMapper;
import com.roman_musijowski.pgs_lessons.util.mappers.RoleMapper;
import com.roman_musijowski.pgs_lessons.util.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile({"dev", "prod"})
public class BootStrapMySQL  implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BootStrapMySQL.class);


    private LessonService lessonService;
    private RoleService roleService;
    private UserService userService;
    private LessonRepositoryImp lessonRepositoryImp;
    private UserMapper userMapper;
    private RoleMapper roleMapper;
    private LessonMapper lessonMapper;

    @Autowired
    public BootStrapMySQL(LessonService lessonService, RoleService roleService, UserService userService
            , LessonRepositoryImp lessonRepositoryImp, UserMapper userMapper
            , RoleMapper roleMapper, LessonMapper lessonMapper) {
        this.lessonService = lessonService;
        this.roleService = roleService;
        this.userService = userService;
        this.lessonRepositoryImp = lessonRepositoryImp;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.lessonMapper = lessonMapper;
    }


//    private void assignUserToLesson() {
//        logger.info("Assign user to lessons");
//        List<User> users = getUsersList();
//        List<Lesson> lessons = getLessonsList();
//
//        users.forEach(user -> {
//            System.out.println(user.toString());
//
//            lessons.forEach(lesson -> {
//                if (lesson.getTitle() == "Java" && user.getUserName() != "admin@gmail.com") {
//                    user.addLesson(lesson);
//
//                }else if (lesson.getTitle() == "Git" && user.getUserName() != "admin@gmail.com"
//                        && user.getUserName() != "plabuda@gmail.com"){
//                    user.addLesson(lesson);
//
//                }else if (lesson.getTitle() == "Spring" && user.getUserName() != "admin@gmail.com"
//                        && user.getUserName() != "plabuda@gmail.com"
//                        && user.getUserName() != "rushla@gmail.com"){
//                    user.addLesson(lesson);
//                }
//            });
//            userService.saveAndReturnDTO( user);
//        });
//    }


    private void loadRoles(){
        logger.info("Load roles");

        RoleDTO admin = new RoleDTO();
        admin.setRole("ADMIN");
        roleService.createNew(admin);

        RoleDTO studen = new RoleDTO();
        studen.setRole("STUDENT");
        roleService.createNew(studen);
    }

    private void loadUsers(){
        logger.info("Load users");

        UserDTO admin = new UserDTO();
        admin.setUserName("admin@gmail.com");
        admin.setName("Admin");
        admin.setSurName("Admin");
        admin.setFieldOfStudy("Informatyka");
        admin.setYearOfStudies(2);
        admin.setIndex("89562312457");
        admin.setPassword("admin");

        userService.createNew(admin);

        UserDTO user = new UserDTO();
        user.setUserName("pgnus@gmail.com");
        user.setName("Piotr");
        user.setSurName("Gnus");
        user.setFieldOfStudy("Informatyka");
        user.setYearOfStudies(2);
        user.setIndex("12345678901");
        user.setPassword("petro");

        userService.createNew(user);

        UserDTO user2 = new UserDTO();
        user2.setUserName("rushla@gmail.com");
        user2.setName("Roman");
        user2.setSurName("Kushla");
        user2.setFieldOfStudy("Informatyka");
        user2.setYearOfStudies(2);
        user2.setIndex("45454545454");
        user2.setPassword("roman");

        userService.createNew(user2);

        UserDTO user3 = new UserDTO();
        user3.setUserName("plabuda@gmail.com");
        user3.setName("Pavlo");
        user3.setSurName("Labuda");
        user3.setFieldOfStudy("Informatyka");
        user3.setYearOfStudies(2);
        user3.setIndex("12235645894");
        user3.setPassword("pavlo");


        userService.createNew(user3);
    }

    private void loadLesson(){
        logger.info("Load lessons");
        LocalDateTime localDateTime = LocalDateTime.of(2019,05,15,18,30);

        LessonDTO lesson = new LessonDTO();
        lesson.setTitle("Java");
        lesson.setDescription("First lesson");
        lesson.setTeacherInfo("Dr. Koziol");
        lesson.setDate(localDateTime);
        lessonService.createNew(lesson);

        localDateTime = LocalDateTime.of(2019,06,11,18,00);

        LessonDTO lesson2 = new LessonDTO();
        lesson2.setTitle("Git");
        lesson2.setDescription("Second lesson");
        lesson2.setTeacherInfo("Dr. URban");
        lesson2.setDate(localDateTime);
        lessonService.createNew(lesson2);

        localDateTime = LocalDateTime.of(2019,07,8,19,00);


        LessonDTO lesson3 = new LessonDTO();
        lesson3.setTitle("Spring");
        lesson3.setDescription("Third lesson");
        lesson3.setTeacherInfo("Dr. Nowojewski");
        lesson3.setDate(localDateTime);
        lessonService.createNew(lesson3);
    }

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadUsers();
        loadLesson();
//        assignUserToLesson();
    }
}
