package com.roman_musijowski.pgs_lessons.services.serviccRestFull;

import com.roman_musijowski.pgs_lessons.controllers.LessonController;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.repositories.LessonRepositoryImp;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.exeptions.ResourceNotFoundException;
import com.roman_musijowski.pgs_lessons.util.dto.LessonDTO;
import com.roman_musijowski.pgs_lessons.util.mappers.LessonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LessonServiceRestImp implements LessonService {

    private final LessonRepositoryImp lessonRepositoryImp;
    private final LessonMapper lessonMapper;

    @Autowired
    public LessonServiceRestImp(LessonRepositoryImp lessonRepositoryImp, LessonMapper lessonMapper) {
        this.lessonRepositoryImp = lessonRepositoryImp;
        this.lessonMapper = lessonMapper;
    }


    @Override
    @Cacheable(value = "lessons")
    @Transactional(readOnly = true)
    public List<LessonDTO> listAll() {
        return lessonRepositoryImp
                .findAll()
                .stream()
                .map(lesson -> {
                    LessonDTO lessonDTO = lessonMapper.lessoneToLessonDTO(lesson);
                    lessonDTO.setLessonUrl(getLessonUrl(lesson.getLessonId()));
                    return lessonDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "lessons", key = "#id")
    @Transactional(readOnly = true)
    public LessonDTO getById(Long id) {
        return lessonRepositoryImp.findById(id)
                .map(lessonMapper::lessoneToLessonDTO)
                .map(lessonDTO -> {
                    lessonDTO.setLessonUrl(getLessonUrl(id));
                    return lessonDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Caching(evict =
            {@CacheEvict(cacheNames = "lessons", allEntries = true),
             @CacheEvict(cacheNames = "users", allEntries = true)})
    public LessonDTO saveAndReturnDTO(Lesson lesson) {
        Lesson savedLesson = lessonRepositoryImp.save(lesson);

        LessonDTO returnDto = lessonMapper.lessoneToLessonDTO(savedLesson);
        returnDto.setLessonUrl(getLessonUrl(savedLesson.getLessonId()));

        return returnDto;
    }

    @Override
    public LessonDTO createNew(LessonDTO lessonDTO) {
        return saveAndReturnDTO(lessonMapper.lessoneDTOToLesson(lessonDTO));
    }

    @Override
    @CacheEvict(cacheNames = "lessons", allEntries = true)
    public LessonDTO putByDTO(Long id, LessonDTO object) {
        Lesson lesson = lessonMapper.lessoneDTOToLesson(object);
        lesson.setLessonId(id);

        return saveAndReturnDTO(lesson);
    }

    @Override
    @CacheEvict(cacheNames = "lessons", allEntries = true)
    public LessonDTO patchByDTO(Long id, LessonDTO object) {
        return lessonRepositoryImp.findById(id).map(lesson -> {
            if (object.getTitle() != null){
                lesson.setTitle(object.getTitle());
            }
            if (object.getDescription() != null){
                lesson.setDescription(object.getDescription());
            }
            if (object.getTeacherInfo() != null){
                lesson.setTeacherInfo(object.getTeacherInfo());
            }
            if (object.getDate() != null){
                lesson.setDate(object.getDate());
            }
            LessonDTO returnDto = lessonMapper.lessoneToLessonDTO(lessonRepositoryImp.save(lesson));
            return returnDto;

        }).orElseThrow(ResourceNotFoundException::new);
    }


    @Override
    @CacheEvict(cacheNames = "lessons", allEntries = true)
    public void deleteById(Long id) {
        lessonRepositoryImp.deleteById(id);
    }

    private String getLessonUrl(Long id) {
        return LessonController.BASE_URL + "/" + id;
    }

}
