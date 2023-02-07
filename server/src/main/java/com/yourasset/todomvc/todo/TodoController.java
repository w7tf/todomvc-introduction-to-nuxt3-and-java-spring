package com.yourasset.todomvc.todo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    private final TodoService todoService;

    /**
     * @param todoService
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/completed")
    public List<Todo> getAllCompletedTodos() {
        return todoService.getAllCompletedTodos();
    }

    @GetMapping("/active")
    public List<Todo> getAllIncompletedTodos() {
        return todoService.getAllIncompletedTodos();
    }

    @PostMapping("/toggle-all")
    public void toggleAllTodos(@RequestBody Todo todo) {
        todoService.toggleAllTodos();
    }

    @PostMapping
    public void createTodo(@RequestBody Todo todo) {
        todoService.createTodo(todo);
    }

    @PostMapping("/{id}")
    public void updateTodoById(@PathVariable("id") String id, @RequestBody Todo todo) throws TodoNotFoundException {
        todoService.updateTodoById(id, todo);
    }


    @DeleteMapping
    public void deleteCompletedTodos() {
        todoService.deleteCompletedTodos();
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable("id") String id) throws TodoNotFoundException {
        todoService.deleteTodoById(id);
    }

}
