package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class TodoTest {

    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    @DisplayName("Check whether the class is properly instantiated.")
    public void testNewInstance() {
        var todo = new Todo("To tidy up the house", false);
        assertNotNull(todo);
    }

    @Test
    @DisplayName("Check whether the JPA entity is properly saved.")
    public void testEntity() {

        var todo = new Todo("To tidy up the house", false);
        
        assertNotNull(todo);
        
        var persistedTodo = entityManager.persist(todo);
        assertNotNull(persistedTodo.getId());
    }
}
