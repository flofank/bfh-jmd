package org.example;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

public class AuthOutInterceptor extends AbstractSoapInterceptor {

  private static final QName HEADER_NAME = new QName("http://example.org/todo", "AuthToken");
  private String token;

  public AuthOutInterceptor(String token) {
    super(Phase.PRE_PROTOCOL);
    this.token = token;
  }

  @Override
  public void handleMessage(SoapMessage message) {
    try {
      Header header = new Header(HEADER_NAME, token, new JAXBDataBinding(String.class));
      message.getHeaders().add(header);
    } catch (JAXBException ex) {
      ex.printStackTrace();
    }
  }
}