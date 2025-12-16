package com.example.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/todo")
public class TodoRestController {

    public static class Todo {
        public int id;
        public String title;
        public String description;
        public int priority;

        public Todo() {
        }

        public Todo(int id, String title, String description, int priority) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.priority = priority;
        }
    }

    private int idGenerator = 1;
    private int newId() {
        return idGenerator++;
    }

    protected List<Todo> todos = new ArrayList<>(List.of(
            new Todo(newId(), "Learn Java", "", 1),
            new Todo(newId(), "Learn SpringBoot", "", 2),
            new Todo(newId(), "Learn Rest Spring-Web", "", 3)
    ));

    // READ - Get all todos
    @GetMapping
    public List<Todo> getAll() {
        return todos;
    }

    // READ - Get todo by ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable int id) {
        Todo todo = todos.stream().filter(t -> t.id == id).findFirst().orElse(null);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todo);
    }

    // CREATE - Add new todo
    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        if (todo == null) {
            return ResponseEntity.badRequest().build();
        }
        todo.id = newId(); // Assign new ID
        todos.add(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    // UPDATE - Update existing todo
    @PutMapping()
    public ResponseEntity<Todo> update(@RequestBody Todo updatedTodo) {
        if (updatedTodo == null) {
            return ResponseEntity.badRequest().build();
        }
        int id = updatedTodo.id;
        boolean removed = todos.removeIf(t -> t.id == id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        todos.add(updatedTodo);
        return ResponseEntity.ok(updatedTodo);
    }

    // DELETE - Delete todo by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean removed = todos.removeIf(t -> t.id == id);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE - Delete all todos
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        todos.clear();
        return ResponseEntity.noContent().build();
    }
}