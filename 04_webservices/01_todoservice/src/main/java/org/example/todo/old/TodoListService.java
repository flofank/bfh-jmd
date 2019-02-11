package org.example.todo.old;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@WebService(name = "TodoList", serviceName = "TodoListService", targetNamespace = "http://example.org/todo")
public class TodoListService {

  @Autowired
  private TodoRepository repository;

  @WebMethod(operationName = "AddTodo")
	public long addTodo(String title, Date dueDate) {
		Todo t = new Todo(title, dueDate);
	  repository.save(t);
	  return t.getId();
	}

	@WebMethod(operationName = "GetTodos")
	public List<Todo> getTodos() {
	  return repository.findAll();
	}

	@WebMethod
	public Todo getTodo(long id) throws TodoNotFoundException {
	  Optional<Todo> t = repository.findById(id);
	  if (t.isPresent()) {
	    return t.get();
    }
    throw new TodoNotFoundException("No todo found with id: " + id);
	}

	@WebMethod
	public void completeTodo(long id) throws TodoNotFoundException {
	  Todo t = getTodo(id);
	  t.setCompleted(true);
	  repository.save(t);
	}

	@Oneway
  @WebMethod
	public void removeTodo(long id) throws TodoNotFoundException {
	  Todo t = getTodo(id);
	  repository.delete(t);
	}
}
