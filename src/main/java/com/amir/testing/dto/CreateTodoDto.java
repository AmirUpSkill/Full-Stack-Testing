package com.amir.testing.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTodoDto {

    @NotBlank(message = "Todo description cannot be empty or Blank ")
    private String description;

}
