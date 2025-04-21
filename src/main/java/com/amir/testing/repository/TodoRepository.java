package com.amir.testing.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amir.testing.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID >{

    

}
