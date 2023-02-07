package com.yourasset.todomvc.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo, String> {

    @Query("SELECT t FROM Todo t WHERE t.isCompleted = true")
    List<Todo> findCompletedTodos();

    @Query("SELECT t FROM Todo t WHERE t.isCompleted = false")
    List<Todo> findActiveTodos();

}
