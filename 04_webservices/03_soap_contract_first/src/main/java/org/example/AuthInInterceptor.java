package org.example;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.logging.Logger;

public class AuthInInterceptor extends AbstractSoapInterceptor {

	private static final Logger LOGGER = Logger.getLogger(AuthInInterceptor.class.getName());
	private static final QName HEADER_NAME = new QName("http://example.org/todo", "AuthToken");

	private String authToken;

	public AuthInInterceptor(String authToken) {
		super(Phase.POST_PROTOCOL);
		this.authToken = authToken;
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		Header header = message.getHeader(HEADER_NAME);
		if (header == null)
			throw new Fault("Missing authentication token", LOGGER);
		String token = ((Element) header.getObject()).getTextContent();
		if (!token.equals(authToken))
			throw new Fault("Invalid authentication token", LOGGER);
		LOGGER.info("Request successfully authenticated");
	}
}
