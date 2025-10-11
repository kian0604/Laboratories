package com.example.todoappbackend.repository;

import com.example.todoappbackend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
