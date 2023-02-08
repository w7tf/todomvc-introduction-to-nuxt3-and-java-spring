package com.yourasset.todomvc.todo;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService service;

    @Mock
    private TodoRepository todoRepository;

    Todo todo = new Todo("Sample Todo", true);

    @Test
    void testCreateTodo() throws Exception {
        this.mockMvc.perform(post("/api/v1/todos", todo).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(todo)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCompletedTodos() throws Exception {
        this.mockMvc.perform(delete("/api/v1/todos/completed")).andExpect(status().isOk());
    }

    @Test
    void testDeleteTodoById() throws Exception {
        this.mockMvc.perform(delete("/api/v1/todos/{id}", 1)).andExpect(status().isOk());
    }

    @Test
    void testGetAllCompletedTodos() throws Exception {
        this.mockMvc.perform(get("/api/v1/todos/completed")).andExpect(status().isOk());
    }

    @Test
    void testGetAllIncompletedTodos() throws Exception {
        this.mockMvc.perform(get("/api/v1/todos/active")).andExpect(status().isOk());
    }

    @Test
    void testGetAllTodos() throws Exception {
        this.mockMvc.perform(get("/api/v1/todos")).andExpect(status().isOk());
    }

    @Test
    void testToggleAllTodos() throws Exception {
        this.mockMvc.perform(patch("/api/v1/todos/toggle-all", todo).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(todo)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTodoById() throws Exception {
        this.mockMvc.perform(patch("/api/v1/todos/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(todo)))
                .andExpect(status().isOk());
    }
}
