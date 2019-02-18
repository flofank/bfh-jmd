package org.example;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static org.apache.cxf.transport.http.AbstractHTTPDestination.HTTP_REQUEST;

public class TraceInterceptor extends AbstractPhaseInterceptor<Message> {

	private static final Logger log = LoggerFactory.getLogger(TraceInterceptor.class);

	public TraceInterceptor() {
		super(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		HttpServletRequest request = (HttpServletRequest) message.get(HTTP_REQUEST);
		log.info("Request received from " + request.getRemoteHost() + ": " +
				request.getMethod() + " " + request.getRequestURI() +
				(request.getQueryString() != null ? "?" + request.getQueryString() : ""));
	}
}
