package com.shchehlov.universityschedule.rest;

import com.shchehlov.universityschedule.dto.LectureDto;
import com.shchehlov.universityschedule.dto.response.LectureDtoResponse;
import com.shchehlov.universityschedule.service.LectureService;
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
import java.util.stream.Collectors;

import static com.shchehlov.universityschedule.utils.BindingResultHandler.handleFieldErrors;

@RestController
@RequestMapping("/api/v1/lectures")
public class LecturesController {

    private final LectureService lectureService;

    @Autowired
    public LecturesController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping
    public List<LectureDtoResponse> getAll() {
        return lectureService.getAll().stream()
                .map(LectureDtoResponse::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LectureDtoResponse getOneById(@PathVariable(name = "id") Long id) {
        return LectureDtoResponse.toDto(lectureService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LectureDtoResponse> create(@RequestBody @Valid LectureDto lectureDto, BindingResult bindingResult) {
        handleFieldErrors(bindingResult);

        return new ResponseEntity<>(LectureDtoResponse.toDto(lectureService.save(lectureDto.getItem())), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid LectureDto lectureDto, BindingResult bindingResult) {
        handleFieldErrors(bindingResult);
        lectureService.update(lectureDto.getItem());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        lectureService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
