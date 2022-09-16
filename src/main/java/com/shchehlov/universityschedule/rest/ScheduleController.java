package com.shchehlov.universityschedule.rest;

import com.shchehlov.universityschedule.dto.ScheduleDto;
import com.shchehlov.universityschedule.dto.response.ScheduleDtoResponse;
import com.shchehlov.universityschedule.service.ScheduleService;
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
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public List<ScheduleDtoResponse> getAll() {
        return scheduleService.getAll().stream()
                .map(ScheduleDtoResponse::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ScheduleDtoResponse getOneById(@PathVariable(name = "id") Long id) {
        return ScheduleDtoResponse.toDto(scheduleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ScheduleDtoResponse> create(@RequestBody @Valid ScheduleDto scheduleDto, BindingResult bindingResult) {
        handleFieldErrors(bindingResult);

        return new ResponseEntity<>(ScheduleDtoResponse.toDto(scheduleService.save(scheduleDto.getItem())), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid ScheduleDto scheduleDto, BindingResult bindingResult) {
        handleFieldErrors(bindingResult);
        scheduleService.update(scheduleDto.getItem());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
