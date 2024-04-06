package com.killuacode.Todoapi.controller;


import com.killuacode.Todoapi.entity.Todo;
import com.killuacode.Todoapi.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/users/")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("{userId}/todos")
    @PreAuthorize("hasAuthority('todos:read') and #userId == authentication.principal.id")
    public List<Todo> retrieveAllTodos(@PathVariable Integer userId) {
        return todoService.retrieveAllTodos(userId);
    }

    @GetMapping("{userId}/todos/{todoId}")
    @PreAuthorize("hasAuthority('todos:read') and #userId == authentication.principal.id")
    public Todo retrieveTodo(
            @PathVariable Integer userId,
            @PathVariable Integer todoId
    ) {
        return todoService.retrieveTodo(userId, todoId);
    }

    @PostMapping("{userId}/todos")
    @PreAuthorize("hasAuthority('todos:write') and #userId == authentication.principal.id")
    public ResponseEntity<Todo> createTodo(
            @RequestBody @Valid Todo todo,
            @PathVariable Integer userId
    ) {
        return todoService.createTodo(todo, userId);
    }

    @DeleteMapping("{userId}/todos/{todoId}")
    @PreAuthorize("hasAuthority('todos:delete') and #userId == authentication.principal.id")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Integer userId,
            @PathVariable Integer todoId
    ) {
        return todoService.deleteTodo(userId, todoId);
    }


    @PutMapping("/{userId}/todos/{todoId}")
    @PreAuthorize("hasAuthority('todos:update') and #userId == authentication.principal.id")
    public ResponseEntity<Todo> updateTodo(
            @RequestBody @Valid Todo todo,
            @PathVariable Integer userId,
            @PathVariable Integer todoId
    ) {
        return todoService.updateTodo(todo, userId, todoId);
    }

}
