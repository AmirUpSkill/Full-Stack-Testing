package com.amir.testing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amir.testing.service.TodoService;
import com.amir.testing.dto.TodoDto;
import com.amir.testing.dto.UpdateTodoDto;
import com.amir.testing.dto.CreateTodoDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@Valid @RequestBody CreateTodoDto createTodoDto) {
        log.info("Received request to create Todo : {}", createTodoDto);

        TodoDto createdTodo = todoService.createTodo(createTodoDto);

        // Build the URI for the Location header
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // Gets the base path (/api/v1/todos)
                .path("/{id}")        // Appends the path variable placeholder
                .buildAndExpand(createdTodo.getId()) // Fills the placeholder with the new ID
                .toUri();
        log.info("Responding with CREATED status for Todo ID : {}", createdTodo.getId());
        return ResponseEntity.created(location).body(createdTodo);
    }
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        log.info("Received request to get all Todos");
        List<TodoDto> todos = todoService.getAllTodos();

        log.info("Responding with OK status  , returning {} Todos",todos.size());
        return ResponseEntity.ok(todos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable UUID id ){
        log.info("Received request to get Todo with ID : {}", id);
        TodoDto todo = todoService.getTodoById(id);

        log.info("Responding with OK status for Todo ID : {}", id);
        return ResponseEntity.ok(todo);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable UUID id , @Valid @RequestBody UpdateTodoDto updateTodoDto){
        log.info("Received request to update Todo with ID : {}", id);
        TodoDto updatedTodo = todoService.updateTodo(id, updateTodoDto);

        log.info("Responding with OK status for Todo ID : {}", id);
        return ResponseEntity.ok(updatedTodo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable UUID id){
        log.info("Received request to delete Todo with ID : {}", id);
        todoService.deleteTodoById(id);

        log.info("Responding with NO_CONTENT status for Todo ID : {}", id);
        return ResponseEntity.noContent().build();
    }
}
