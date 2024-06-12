package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class TodoRestController {
    private final TodoRepository repository;

    public TodoRestController(final TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todos")
    public Flux<Todo> readTodos() {
        return repository.findAll();
    }
    
    @GetMapping("/todos/{done}")
    public Flux<Todo> readByDone(@PathVariable boolean done) {
        return repository.findByDone(done);
    }

    @PostMapping("/todos")
    public Mono<Todo> create(@RequestBody Todo todo) {
        return repository.save(todo);
    }

    @DeleteMapping("/todos/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return repository.deleteById(id);
    }

    @PutMapping("/todos/{id}")
    public Mono<Todo> update(@PathVariable String id) {
        return repository
                .findById(id)
                .map(currentTodo -> new Todo(id, currentTodo.title(), currentTodo.description(), !currentTodo.done()))
                .flatMap(repository::save)
                .onTerminateDetach();
    }
}
