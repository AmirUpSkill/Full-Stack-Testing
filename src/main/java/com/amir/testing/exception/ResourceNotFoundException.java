package com.amir.testing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception thrown when a requested resource (e.g., a Todo item)
 * cannot be found in the system.
 *
 * Annotated with @ResponseStatus(HttpStatus.NOT_FOUND) so that Spring MVC
 * automatically translates this exception into a 404 Not Found HTTP response
 * if it bubbles up unhandled to the controller layer.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) // Maps this exception to HTTP 404 Not Found
public class ResourceNotFoundException extends RuntimeException { // Inherit from RuntimeException (unchecked)

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public ResourceNotFoundException(String message) {
        super(message); // Call the RuntimeException constructor
    }

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause (which is saved for later retrieval by the getCause() method).
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause); // Call the RuntimeException constructor
    }
}