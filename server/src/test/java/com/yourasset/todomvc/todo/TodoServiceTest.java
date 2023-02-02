package com.yourasset.todomvc.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
    private TodoService underTest;

    @BeforeEach
    void setUp() throws Exception {
        underTest = new TodoService(todoRepository);
    }

    @Test
    void testCreateTodo() {
        Todo todo = new Todo("test", false);

        underTest.createTodo(todo);

        ArgumentCaptor<Todo> todoArgumentCaptor = ArgumentCaptor.forClass(Todo.class);

        verify(todoRepository).save(todoArgumentCaptor.capture());

        Todo capturedTodo = todoArgumentCaptor.getValue();

        capturedTodo.setTitle("edited");

        assertEquals(todo, capturedTodo);
    }

    @Test
    void testDeleteCompletedTodos() {

    }

    @Test
    void testDeleteTodoById() {
        assertThrows(TodoNotFoundException.class, () -> underTest.deleteTodoById("1"));
    }

    @Test
    void testGetAllCompletedTodos() {
        underTest.getAllCompletedTodos();
        verify(todoRepository).findAll();
    }

    @Test
    void testGetAllIncompletedTodos() {

    }

    @Test
    void testGetAllTodos() {

    }

    @Test
    void testToggleAllTodos() {

    }

    @Test
    void testUpdateTodoById() {

    }
}
