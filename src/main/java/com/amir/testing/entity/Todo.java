package com.amir.testing.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
// import lombok.Getter; // Remove this or keep if you prefer explicit @Setter
import lombok.NoArgsConstructor;
import lombok.Setter; // Add this if you don't use @Data
import lombok.Data; // Replace @Getter and @Setter with this for convenience

@Data // <- USE THIS: Includes @Getter, @Setter, @ToString, @EqualsAndHashCode
@NoArgsConstructor // Required by JPA
@AllArgsConstructor // Convenient constructor
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private boolean completed = false; // Default value is good

    // No need to manually write getters or setters - Lombok @Data handles it
}