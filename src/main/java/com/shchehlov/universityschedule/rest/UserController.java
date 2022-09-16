package com.shchehlov.universityschedule.rest;

import com.shchehlov.universityschedule.dto.UserDto;
import com.shchehlov.universityschedule.dto.response.UserDtoResponse;
import com.shchehlov.universityschedule.model.Lecture;
import com.shchehlov.universityschedule.model.User;
import com.shchehlov.universityschedule.model.attributes.WeekDay;
import com.shchehlov.universityschedule.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.shchehlov.universityschedule.utils.BindingResultHandler.handleFieldErrors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDtoResponse> getAll() {
        return userService.getAll().stream()
                .map(UserDtoResponse::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDtoResponse getOneById(@PathVariable(name = "id") Long id) {
        return UserDtoResponse.toDto(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserDtoResponse> create(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        handleFieldErrors(bindingResult);
        User user = userService.save(userDto.getItem());
        return new ResponseEntity<>(UserDtoResponse.toDto(user), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        handleFieldErrors(bindingResult);
        userService.update(userDto.getItem());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/lectures")
    List<Lecture> getLectures(@PathVariable("id") Long id, @RequestParam("day") WeekDay weekDay) {
        return userService.getLecturesScheduleForWeekDay(id, weekDay);
    }
}
