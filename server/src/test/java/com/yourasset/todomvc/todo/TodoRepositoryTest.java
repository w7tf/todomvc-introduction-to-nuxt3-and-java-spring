package com.yourasset.todomvc.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void checkIfAddedTodosCanBeFound() {
        Todo todo = new Todo("test", true);
        todoRepository.save(todo);
        todoRepository.findAll().forEach(item -> assertEquals("test", todo.getTitle()));
    }

    @Test
    void checkIfAddedTodosCanBeFoundById() {
        Todo todo = new Todo();
        todo.setTitle("test");
        todo.setCompleted(true);
        todoRepository.save(todo);
        todoRepository.findById(todo.getId()).ifPresent(item -> assertEquals("test", item.getTitle()));
    }

    @Test
    void checkIfAddedTodosHaveCreatedAt() {
        Todo todo = new Todo();
        todoRepository.save(todo);
        todoRepository.findById(todo.getId()).ifPresent(item -> assertEquals(todo.getCreatedAt(), item.getCreatedAt()));
    }

    @Test
    void checkIfAddedTodoHasUpdatedAt() {
        Todo todo = new Todo();
        todoRepository.save(todo);
        todoRepository.findById(todo.getId()).ifPresent(item -> assertEquals(todo.getUpdatedAt(), item.getUpdatedAt()));
    }

}
