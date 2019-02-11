package org.example.todo;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TodoListServiceIT {

	@Autowired
	private TodoListService todoListService;

	@Test
	public void addTodo() throws TodoNotFoundException {
		long id = todoListService.addTodo("Test web service", new Date());
		Todo todo = todoListService.getTodo(id);
		assertThat(todo.getTitle(), is("Test web service"));
		assertThat(todo.getDueDate(), isToday());
	}

	@Test
	public void getTodos() {
		todoListService.addTodo("Test web service", new Date());
		todoListService.addTodo("Test web service again", null);
		List<Todo> todos = todoListService.getTodos();
		assertThat(todos, hasSize(2));
	}

	@Test
	public void completeTodo() throws TodoNotFoundException {
		long id = todoListService.addTodo("Test web service", new Date());
		todoListService.completeTodo(id);
		Todo todo = todoListService.getTodo(id);
		assertThat(todo.isCompleted(), is(true));
	}

	@Test
	public void removeTodo() throws TodoNotFoundException {
		long id = todoListService.addTodo("Test web service", new Date());
		todoListService.removeTodo(id);
		List<Todo> todos = todoListService.getTodos();
		assertThat(todos, is(empty()));
	}

	private static Matcher<Date> isToday() {
		return new IsTodayMatcher();
	}
}
