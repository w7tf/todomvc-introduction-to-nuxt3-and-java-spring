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
    void shouldFindCompletedTodos() {
        Todo todo1 = new Todo("1", true);
        todoRepository.save(todo1);

        Todo todo2 = new Todo("2", false);
        todoRepository.save(todo2);

        assertEquals(1, todoRepository.findCompletedTodos().size());
    }

    @Test
    void shouldFindActiveTodos() {
        Todo todo1 = new Todo("1", true);
        todoRepository.save(todo1);

        Todo todo2 = new Todo("2", false);
        todoRepository.save(todo2);

        assertEquals(1, todoRepository.findActiveTodos().size());
    }

}
