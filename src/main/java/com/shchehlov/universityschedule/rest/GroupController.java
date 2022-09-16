package com.shchehlov.universityschedule.rest;

import com.shchehlov.universityschedule.dto.GroupDto;
import com.shchehlov.universityschedule.dto.response.GroupDtoResponse;
import com.shchehlov.universityschedule.service.GroupService;
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
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<GroupDtoResponse> getAll() {
        return groupService.getAll().stream()
                .map(GroupDtoResponse::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GroupDtoResponse getOneById(@PathVariable(name = "id") Long id) {
        return GroupDtoResponse.toDto(groupService.getById(id));
    }

    @PostMapping
    public ResponseEntity<GroupDtoResponse> create(@RequestBody @Valid GroupDto groupDto, BindingResult bindingResult) {
        handleFieldErrors(bindingResult);
        return new ResponseEntity<>(GroupDtoResponse.toDto(groupService.save(groupDto.getItem())), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid GroupDto groupDto, BindingResult bindingResult) {
        handleFieldErrors(bindingResult);
        groupService.update(groupDto.getItem());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
