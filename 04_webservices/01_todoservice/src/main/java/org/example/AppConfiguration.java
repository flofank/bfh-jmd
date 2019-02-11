package org.example;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.example.todo.TodoListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class AppConfiguration {
	@Bean
	public Endpoint endpoint(Bus bus, TodoListService service) {
		EndpointImpl endpoint = new EndpointImpl(bus, service);
		endpoint.publish("/todo");
		return endpoint;
	}
}
