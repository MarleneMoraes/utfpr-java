package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository repository;

    @GetMapping("/")
    public String getTodos(Model memory) {
        memory.addAttribute("todos", repository.findAll());

        return "/todo";
    }

    @PostMapping("/add") 
    public String createTodo(Todo aTodo) {
        repository.save(aTodo);

        return "redirect:/";
    }

    @GetMapping("/remove")
    public String deleteTodo(@RequestParam int id) {
        repository.deleteById(Long.parseLong(id+""));

        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateTodo(@RequestParam int id) {
        repository.findById(Long.parseLong(id + "")).ifPresent(todo -> todo.setStatus(true));
        repository.flush();

        return "redirect:/";
    }
}
