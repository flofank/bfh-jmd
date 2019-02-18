package org.example.todo;

import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@SchemaValidation
@WebService(endpointInterface = "org.example.todo.TodoList", wsdlLocation = "classpath:/wsdl/TodoListService.wsdl")
@InInterceptors(interceptors = {"org.example.TraceInterceptor", "org.apache.cxf.ext.logging.LoggingInInterceptor"})
@OutInterceptors(interceptors = "org.apache.cxf.ext.logging.LoggingOutInterceptor")
public class TodoListImpl implements TodoList {

	private static final Logger log = LoggerFactory.getLogger(TodoListImpl.class);

	@Autowired
	private TodoRepository todoRepository;

	public long addTodo(String title, XMLGregorianCalendar calendar) {
		TodoEntity entity = new TodoEntity(title, map(calendar));
		todoRepository.saveAndFlush(entity);
		log.info("Todo with id " + entity.getId() + " added");
		return entity.getId();
	}

	public List<Todo> getTodos() {
		return todoRepository.findAll().stream().map(entity -> map(entity)).collect(toList());
	}

	public void completeTodo(long id) throws TodoNotFoundException {
		TodoEntity entity = todoRepository.findById(id)
				.orElseThrow(() -> new TodoNotFoundException("Todo with id " + id + " not found"));
		entity.setCompleted(true);
		todoRepository.save(entity);
		log.info("Todo with id " + id + " completed");
	}

	public void removeTodo(long id) {
		todoRepository.findById(id).ifPresent(entity -> todoRepository.delete(entity));
		log.info("Todo with id " + id + " deleted");
	}

	private Todo map(TodoEntity entity) {
		Todo todo = new Todo();
		todo.setId(entity.getId());
		todo.setTitle(entity.getTitle());
		todo.setDueDate(map(entity.getDueDate()));
		todo.setCompleted(entity.isCompleted());
		return todo;
	}

	private Date map(XMLGregorianCalendar calendar) {
		try {
			return calendar.toGregorianCalendar().getTime();
		} catch (Exception ex) {
			return null;
		}
	}

	private XMLGregorianCalendar map(Date date) {
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			calendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			return calendar;
		} catch (Exception ex) {
			return null;
		}
	}
}
