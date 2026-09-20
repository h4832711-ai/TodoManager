package com.todomanager.business;

import java.util.ArrayList;
import java.util.List;

import com.todomanager.data.api.TodoService;

public class TodoBusinessImpl {

	private TodoService todoService;

	TodoBusinessImpl(TodoService todoService) {
		this.todoService = todoService;
	}

	public List<String> retrieveTodosRelatedToSpring(String user) {
		List<String> filteredTodos = new ArrayList<String>();
		List<String> allTodos = todoService.retrieveTodos(user);
		for (String todo : allTodos) {
			if (todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}

	public void deleteTodosNotRelatedToSpring(String user) {
		List<String> allTodos = todoService.retrieveTodos(user);
		for (String todo : allTodos) {
			if (!todo.contains("Spring")) {
				todoService.deleteTodo(todo);
			}
		}
	}
	public static void main(String[] args) {
		com.todomanager.data.SimpleTodoService service =
				new com.todomanager.data.SimpleTodoService(
						java.util.Arrays.asList(
								"Learn Spring",
								"Learn Java",
								"Practice Spring Boot"
						)
				);
	
		TodoBusinessImpl manager = new TodoBusinessImpl(service);
	
		System.out.println("All tasks:");
		System.out.println(service.retrieveTodos("student"));
	
		System.out.println("\nSpring tasks:");
		System.out.println(
				manager.retrieveTodosRelatedToSpring("student")
		);
	
		manager.deleteTodosNotRelatedToSpring("student");
	
		System.out.println("\nTasks after deleting non-Spring tasks:");
		System.out.println(service.retrieveTodos("student"));
	}
}
