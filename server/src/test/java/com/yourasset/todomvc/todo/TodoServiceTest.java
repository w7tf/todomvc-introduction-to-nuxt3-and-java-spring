package com.yourasset.todomvc.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;

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
    @Mock
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

        assertEquals(todo, capturedTodo);
    }

    @Test
    void testDeleteCompletedTodos() {

        List<Todo> completedTodos = List.of(new Todo("Completed", true), new Todo("completed 2", true));

        given(todoRepository.findCompletedTodos()).willReturn(completedTodos);

        underTest.deleteCompletedTodos();

        verify(todoRepository).deleteAll(completedTodos);
    }

    @Test
    void testWillThrowExceptionWhenIdNotFound() {
        assertThrows(TodoNotFoundException.class, () -> underTest.deleteTodoById("1"));
    }

    @Test
    void testDeleteTodoById() throws TodoNotFoundException {

        String id = "1";
        given(todoRepository.existsById(id)).willReturn(true);

        underTest.deleteTodoById(id);

        verify(todoRepository).deleteById(id);
    }

    @Test
    void testGetAllCompletedTodos() {
        List<Todo> completedTodos = List.of(new Todo("Completed", true), new Todo("completed 2", true));
        given(todoRepository.findCompletedTodos()).willReturn(completedTodos);
        underTest.getAllCompletedTodos();
        verify(todoRepository).findCompletedTodos();

        assertEquals(completedTodos, underTest.getAllCompletedTodos());
    }

    @Test
    void testGetAllIncompletedTodos() {

        List<Todo> incompletedTodos = List.of(new Todo("Incompleted", false), new Todo("Incompleted 2", false));
        given(todoRepository.findActiveTodos()).willReturn(incompletedTodos);
        underTest.getAllIncompletedTodos();
        verify(todoRepository).findActiveTodos();

        assertEquals(incompletedTodos, underTest.getAllIncompletedTodos());
    }

    @Test
    void testGetAllTodos() {

        List<Todo> todos = List.of(new Todo("Todo 1", false), new Todo("Todo 2", true));
        given(todoRepository.findAll()).willReturn(todos);
        underTest.getAllTodos();
        verify(todoRepository).findAll();

        assertEquals(todos, underTest.getAllTodos());
    }

    @Test
    void testToggleAllTodosToBeCompleted() {

        List<Todo> todos = List.of(new Todo("Todo 1", false), new Todo("Todo 2", true));
        given(todoRepository.findAll()).willReturn(todos);
        underTest.toggleAllTodos();
        verify(todoRepository).findAll();

        for (Todo todo : todos) {
            assertTrue(todo.isCompleted());
        }

    }
    @Test
    void testToggleAllTodosToBeIncomplete() {

        List<Todo> todos = List.of(new Todo("Todo 1", true), new Todo("Todo 2", true));
        given(todoRepository.findAll()).willReturn(todos);
        underTest.toggleAllTodos();
        verify(todoRepository).findAll();

        for (Todo todo : todos) {
            assertTrue(!todo.isCompleted());
        }

    }

    @Test
    void testUpdateTodoById() throws TodoNotFoundException {

        String id = "hasId";
        Todo todo = new Todo();
        todo.setId(id);
        given(todoRepository.findById(id)).willReturn(java.util.Optional.of(todo));
        given(todoRepository.existsById(id)).willReturn(true);

        underTest.updateTodoById(id, todo);

        verify(todoRepository).save(todo);

    }

    @Test
    void testUpdateTodoByIdNotFoundException() throws TodoNotFoundException {

        assertThrows(TodoNotFoundException.class, () -> underTest.deleteTodoById("1"));

    }


}
