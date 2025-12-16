package com.example.demo.rest;

import com.example.demo.entity.TodoEntity;
import com.example.demo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/todo")
@RequiredArgsConstructor
public class TodoRestController {

    private final TodoService delegate;

    // READ - Get all todos
    @GetMapping
    public List<TodoEntity> getAll() {
        return delegate.getAll();
    }

    // READ - Get todo by ID
    @GetMapping("/{id}")
    public ResponseEntity<TodoEntity> getById(@PathVariable int id) {
        TodoEntity todo = delegate.getById(id);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todo);
    }

    // CREATE - Add new todo
    @PostMapping
    public ResponseEntity<TodoEntity> create(@RequestBody TodoEntity todo) {
        if (todo == null) {
            return ResponseEntity.badRequest().build();
        }
        val res = delegate.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    // UPDATE - Update existing todo
    @PutMapping()
    public ResponseEntity<TodoEntity> update(@RequestBody TodoEntity updatedTodo) {
        if (updatedTodo == null) {
            return ResponseEntity.badRequest().build();
        }
        val res = delegate.updateTodo(updatedTodo);
        return ResponseEntity.ok(res);
    }

//    // DELETE - Delete todo by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable int id) {
//        boolean removed = todos.removeIf(t -> t.id == id);
//        if (removed) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

//    // DELETE - Delete all todos
//    @DeleteMapping
//    public ResponseEntity<Void> deleteAll() {
//        todos.clear();
//        return ResponseEntity.noContent().build();
//    }
}