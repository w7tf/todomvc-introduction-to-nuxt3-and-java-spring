package com.yourasset.todomvc.todo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo, String> {

    @Query("SELECT t FROM Todo t WHERE t.isCompleted = ?1")
    Optional<Todo> findCompleted(boolean isCompleted);

}
