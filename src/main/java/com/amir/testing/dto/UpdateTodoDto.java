package com.amir.testing.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for updating Todo items
 * This class is used to transfer todo update data between different layers of the application
 */
@Data
@NoArgsConstructor
public class UpdateTodoDto {

    /**
     * The updated description of the todo item
     * Cannot be empty or blank when provided for update
     */
    private String description;

    /**
     * The updated completion status of the todo item
     * true if the todo is completed, false if not completed
     */
    private Boolean completed;

}
