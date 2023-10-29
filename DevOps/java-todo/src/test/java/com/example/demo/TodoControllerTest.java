package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
public class TodoControllerTest {
    
    @MockBean
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTodos() throws Exception {

        Todo todo = new Todo(1L, "To wash the car", false);
        
        Mockito
            .when(todoRepository.findAll())
            .thenReturn(List.of(todo));

        mockMvc
            .perform(get("/"))
            .andExpect(view().name("/todo"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/HTML;charset=UTF-8"))
            .andExpect(model().attributeExists("todos"));
    }

    @Test
    public void createTodo() throws Exception {
        mockMvc
            .perform(
                post("/add")
                    .param("task", "To wash the car"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));
    }

    @Test
    public void deleteTodo() throws Exception {

        mockMvc
            .perform(
                get("/remove")
                    .queryParam("id", "1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));

    }

    @Test
    public void updateTodo() throws Exception {

        mockMvc
            .perform(
                get("/update")
                    .queryParam("id", "1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));

    }
}
