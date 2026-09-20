package com.todomanager.data;

import java.util.ArrayList;
import java.util.List;

import com.todomanager.data.api.TodoService;

public class SimpleTodoService implements TodoService {

    private final List<String> todos;

    public SimpleTodoService(List<String> initialTodos) {
        this.todos = new ArrayList<>(initialTodos);
    }

    @Override
    public List<String> retrieveTodos(String user) {
        return new ArrayList<>(todos);
    }

    @Override
    public void deleteTodo(String todo) {
        todos.remove(todo);
    }
}