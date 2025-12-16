package com.example.demo.service;

import com.example.demo.entity.TodoEntity;
import com.example.demo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TodoService {

    protected final TodoRepository repository;

    public TodoEntity saveTodo(String description) {
         TodoEntity e = new TodoEntity();
         e.setDescription(description);
         return repository.save(e);
    }

    public List<TodoEntity> findAll() {
        return repository.findAll();
    }
}
