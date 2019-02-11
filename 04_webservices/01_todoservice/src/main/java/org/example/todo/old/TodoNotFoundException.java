package org.example.todo.old;

import javax.xml.ws.WebFault;

@WebFault
public class TodoNotFoundException extends Exception {

	public TodoNotFoundException(String message) {
		super(message);
	}
}
