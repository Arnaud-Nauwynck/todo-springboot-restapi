package com.example.demo.service;

import com.example.demo.entity.TodoEntity;
import com.example.demo.repository.TodoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TodoService {

    protected final TodoRepository repository;

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            log.info("No todos found in database. Creating sample todos...");
            saveTodo("Learn Java");
            saveTodo("Learn SpringBoot");
            saveTodo("Learn Rest Spring-Web");
            for(int i = 1; i < 100; i++) {
                saveTodo("Sample Todo " + i);
            }

        }
    }

    public TodoEntity saveTodo(String description) {
         TodoEntity e = new TodoEntity();
         e.setDescription(description);
         return repository.save(e);
    }

    public List<TodoEntity> findAll() {
        return repository.findAll();
    }

    public List<TodoEntity> getAll() {
        return repository.findAll();
    }

    public TodoEntity getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public TodoEntity createTodo(TodoEntity src) {
        TodoEntity res = new TodoEntity();
        res.setDescription(src.getDescription());
        return repository.save(res);
    }

    public TodoEntity updateTodo(TodoEntity src) {
        val entity = repository.findById(src.getId()).orElseThrow();
        entity.setDescription(src.getDescription());
        return entity;
    }
}
