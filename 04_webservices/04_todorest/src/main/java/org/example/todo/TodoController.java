package org.example.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

	private static final Logger log = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private TodoRepository todoRepository;

	@PostMapping
	public Todo addTodo(@RequestBody Todo todo) {
		todo = todoRepository.save(todo);
		log.info("Todo with id " + todo.getId() + " added");
		return todo;
	}

	@GetMapping
	public List<Todo> getTodos() {
		return todoRepository.findAll();
	}

	@GetMapping("{id}")
	public Todo getTodo(@PathVariable long id) throws TodoNotFoundException {
		return todoRepository.findById(id)
				.orElseThrow(() -> new TodoNotFoundException("Todo with id " + id + " not found"));
	}

	@PutMapping("{id}")
	public Todo updateTodo(@PathVariable long id, @RequestBody Todo todo) throws TodoNotFoundException {
		if (todo.getId() == null) todo.setId(id);
		if (todo.getId() != id)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo has an invalid id");
		getTodo(todo.getId());
		todo = todoRepository.save(todo);
		log.info("Todo with id " + todo.getId() + " updated");
		return todo;
	}

	@DeleteMapping("{id}")
	public void removeTodo(@PathVariable long id) {
		todoRepository.findById(id).ifPresent(todo -> todoRepository.delete(todo));
		log.info("Todo with id " + id + " deleted");
	}

	@ExceptionHandler
	public ResponseEntity<String> handle(TodoNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
