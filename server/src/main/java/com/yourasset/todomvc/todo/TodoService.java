package com.yourasset.todomvc.todo;

import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class TodoService {

    public List<Todo> getAllTodos() {
        return List.of(new Todo(
                "Learn Spring Boot",
                false));
    }

}
