package com.yourasset.todomvc.todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Todo> getAllCompletedTodos() {
        Optional<Todo> completedTodos = todoRepository.findCompleted(true);
        return completedTodos.isPresent() ? List.of(completedTodos.get()) : List.of();
    }

    public void createTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public void deleteCompletedTodos() {
        todoRepository.deleteAllInBatch(getAllCompletedTodos());
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

}
