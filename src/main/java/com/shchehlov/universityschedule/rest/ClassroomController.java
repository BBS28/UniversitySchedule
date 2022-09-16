package com.shchehlov.universityschedule.rest;

import com.shchehlov.universityschedule.dto.ClassroomDto;
import com.shchehlov.universityschedule.dto.response.ClassroomDtoResponse;
import com.shchehlov.universityschedule.model.Classroom;
import com.shchehlov.universityschedule.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.shchehlov.universityschedule.utils.BindingResultHandler.handleFieldErrors;

@RestController
@RequestMapping("/api/v1/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<Classroom> getAll() {
        return classroomService.getAll();
    }

    @GetMapping("/{id}")
    public ClassroomDtoResponse getOneById(@PathVariable(name = "id") Long id) {
        return ClassroomDtoResponse.toDto(classroomService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ClassroomDtoResponse> create(@RequestBody @Valid ClassroomDto classroomDto, BindingResult bindingResult) {
        handleFieldErrors(bindingResult);

        return new ResponseEntity<>(ClassroomDtoResponse.toDto(classroomService.save(classroomDto.getItem())), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid ClassroomDto classroomDto, BindingResult bindingResult) {
        handleFieldErrors(bindingResult);
        classroomService.update(classroomDto.getItem());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        classroomService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
