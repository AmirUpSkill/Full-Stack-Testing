package com.amir.testing.service;

import com.amir.testing.dto.CreateTodoDto;
import com.amir.testing.dto.TodoDto;
import com.amir.testing.dto.UpdateTodoDto;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    /**
     * Creates a new Todo item in the system.
     * 
     * This method validates and processes the provided data to create a new Todo entry.
     * The created Todo will have a unique identifier automatically assigned.
     *
     * @param createTodoDto Data transfer object containing the initial todo information
     * @return TodoDto A DTO containing all details of the newly created Todo item
     * @throws IllegalArgumentException if the provided data is invalid
     */
    TodoDto createTodo(CreateTodoDto createTodoDto);

    /**
     * Retrieves a comprehensive list of all Todo items in the system.
     * 
     * This method fetches all Todos regardless of their completion status.
     * The list is returned in the order of creation, with the most recent items first.
     *
     * @return List<TodoDto> A collection of all Todo items, empty list if no Todos exist
     */
    List<TodoDto> getAllTodos();

    /**
     * Fetches a specific Todo item using its unique identifier.
     * 
     * This method performs a precise lookup based on the provided UUID.
     *
     * @param id Unique identifier of the desired Todo item
     * @return TodoDto Complete details of the requested Todo item
     * @throws com.amir.testing.exception.ResourceNotFoundException if the Todo item doesn't exist
     * @throws IllegalArgumentException if the provided UUID is invalid
     */
    TodoDto getTodoById(UUID id);

    /**
     * Modifies an existing Todo item with new information.
     * 
     * This method implements partial updates, meaning only the fields provided in the DTO
     * will be updated. Existing values are preserved for any fields not specified in the update DTO.
     * The update operation is atomic - either all valid changes are applied, or none are.
     *
     * @param id Unique identifier of the Todo item to update
     * @param updateTodoDto DTO containing the fields to be updated (description and/or completed status)
     * @return TodoDto Updated Todo item with all changes applied
     * @throws com.amir.testing.exception.ResourceNotFoundException if the Todo item doesn't exist
     * @throws IllegalArgumentException if the update data is invalid
     */
    TodoDto updateTodo(UUID id, UpdateTodoDto updateTodoDto);

    /**
     * Permanently removes a Todo item from the system.
     * 
     * This operation is irreversible. Once a Todo is deleted, it cannot be recovered.
     * If the specified Todo doesn't exist, the operation is considered successful.
     *
     * @param id Unique identifier of the Todo item to remove
     * @throws com.amir.testing.exception.ResourceNotFoundException if the Todo item doesn't exist
     * @throws IllegalArgumentException if the provided UUID is invalid
     */
    void deleteTodoById(UUID id);
}
