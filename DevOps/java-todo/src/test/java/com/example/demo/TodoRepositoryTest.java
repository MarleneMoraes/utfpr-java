package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoRepositoryTest {
    
    @Autowired
    private TodoRepository todoRepository;

    @BeforeAll
    public void setup() {
        Todo todo = new Todo();
        todo.setTask("To tidy up the house");
        todo.setStatus(false);
        todoRepository.save(todo);
    }

    @Test
    @DisplayName("Checks whether the task exists.")
    public void testTaskExists() {
        var qtty = todoRepository.count();
        assertEquals(1L, qtty);
    }
    
    @Test
    @DisplayName("Checks whether the task can be retrieved.")
    public void testRetrieval() {
        
        var todo = todoRepository.findById(1L).get();
        assertEquals("To tidy up the house", todo.getTask());
    }
}
