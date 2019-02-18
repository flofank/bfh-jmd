package org.example;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.example.todo.TodoListImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

	@Value("${auth.token}")
	private String authToken;

	@Bean
	public Endpoint endpoint(Bus bus, TodoListImpl todoListImpl) {
		EndpointImpl endpoint = new EndpointImpl(bus, todoListImpl);
		//endpoint.getInInterceptors().add(new AuthInInterceptor(authToken));
		endpoint.publish("/todo");
		return endpoint;
	}
}
