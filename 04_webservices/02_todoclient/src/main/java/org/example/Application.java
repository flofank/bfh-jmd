package org.example;

import org.example.todo.Todo;
import org.example.todo.TodoList;
import org.example.todo.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Value("${endpoint.address}")
	private String endpointAddress;
	@Value("${date.format}")
	private String dateFormat;

	@Autowired
	private TodoList todoList;

  @Bean
  public TodoList todoList() {
    TodoListService todoListService = new TodoListService();
    TodoList todoList = todoListService.getTodoListPort();
    ((BindingProvider) todoList).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
    return todoList;
  }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("1 - List Todos");
			System.out.println("2 - Add Todo");
			System.out.println("3 - Complete Todo");
			System.out.println("4 - Remove Todo");
			System.out.println("x - Exit");
			System.out.print("> ");
			try {
				switch (scanner.nextLine()) {
					case "1":
						todoList.getTodos().forEach(todo -> print(todo));
						break;
					case "2":
						System.out.print("Title:    ");
						String title = scanner.nextLine();
						System.out.print("Due Date: ");
            XMLGregorianCalendar dueDate = parse(scanner.nextLine());
						long id = todoList.addTodo(title, dueDate);
						System.out.println("Todo " + id + " added");
						break;
					case "3":
						try {
							System.out.print("Id: ");
							id = Long.parseLong(scanner.nextLine());
							todoList.completeTodo(id);
							System.out.println("Todo " + id + " completed");
						} catch (NumberFormatException ex) {
						}
						break;
					case "4":
						try {
							System.out.print("Id: ");
							id = Long.parseLong(scanner.nextLine());
							todoList.removeTodo(id);
							System.out.println("Todo " + id + " sremoved");
						} catch (NumberFormatException ex) {
						}
						break;
					case "x":
						System.exit(0);
				}
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
			}
		}
	}

	private void print(Todo todo) {
		System.out.println("Todo " + todo.getId() + ": " + todo.getTitle() + ", " +
				(todo.getDueDate() != null ? format(todo.getDueDate()) + ", " : "") +
				(todo.isCompleted() ? "completed" : "pending"));
	}

  private String format(XMLGregorianCalendar calendar) {
    Date date = calendar.toGregorianCalendar().getTime();
    return new SimpleDateFormat(dateFormat).format(date);
  }

  private XMLGregorianCalendar parse(String source) {
    try {
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.setTime(new SimpleDateFormat(dateFormat).parse(source));
      return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    } catch (ParseException | DatatypeConfigurationException ex) {
      return null;
    }
  }
}
