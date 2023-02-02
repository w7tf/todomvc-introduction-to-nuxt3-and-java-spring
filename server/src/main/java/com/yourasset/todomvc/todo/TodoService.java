package com.yourasset.todomvc.todo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public void createTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public void deleteCompletedTodos() {
        todoRepository.findAll().stream().filter(Todo::isCompleted).forEach(todo -> {
            todoRepository.deleteById(todo.getId());
        });
    }

    public void deleteTodoById(String id) throws TodoNotFoundException {

        boolean todoExists = todoRepository.existsById(id);

        if (!todoExists) {
            throw new TodoNotFoundException("Todo with id " + id + " does not exist");
        }

        if (id != null) {
            todoRepository.deleteById(id);
        }
    }

    public void updateTodoById(String id, Todo todo) throws TodoNotFoundException {

        Todo existingTodo = todoRepository.findById(id).get();

        if (id == null) {
            throw new TodoNotFoundException("Todo with id " + id + " does not exist");
        }

        if (todo.getTitle() != null && !todo.getTitle().isEmpty() && todo.getTitle() != existingTodo.getTitle()) {
            existingTodo.setTitle(todo.getTitle());
        }

        existingTodo.setCompleted(todo.isCompleted());
        existingTodo.setUpdatedAt(LocalDateTime.now());
        todoRepository.save(existingTodo);

    }

    public void toggleAllTodos(Todo todo) {
        List<Todo> allTodos = getAllTodos();

        if (allTodos.stream().allMatch(Todo::isCompleted)) {
            allTodos.forEach(t -> {
                t.setCompleted(false);
                t.setUpdatedAt(LocalDateTime.now());
            });
        } else {
            allTodos.forEach(t -> {
                t.setCompleted(true);
                t.setUpdatedAt(LocalDateTime.now());
            });
        }
        todoRepository.saveAll(allTodos);
    }

    public List<Todo> getAllCompletedTodos() {
        List<Todo> allTodos = getAllTodos();
        allTodos.removeIf(todo -> !todo.isCompleted());
        return allTodos;
    }

    public List<Todo> getAllIncompletedTodos() {
        List<Todo> allTodos = getAllTodos();
        allTodos.removeIf(todo -> todo.isCompleted());
        return allTodos;
    }

}
