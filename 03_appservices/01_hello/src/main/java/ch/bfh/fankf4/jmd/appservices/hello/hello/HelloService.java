package ch.bfh.fankf4.jmd.appservices.hello.hello;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Calendar;

@Service
public class HelloService {
  private Logger LOG = LoggerFactory.getLogger(HelloService.class);

  @Autowired
  private GreetingService greetingService;

  @Autowired
  private GreetingConfiguration config;

  public String sayHello() {
    return "Hello";
  }

  private String sayHello(int age) {
    if (age < config.getAdultAge()) {
      return "Hi";
    }
    return greetingService.getDayTimeGreeting(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
  }

  public String sayHello(String name) {
    return StringUtils.join(sayHello(), " ", name);
  }

  public String sayHello(String name, int age) {
    return StringUtils.join(sayHello(age), " ", name);
  }

  @PostConstruct
  public void helloHelloService() {
    LOG.info(">>> HelloService reporting for duty!");
  }

  @PreDestroy
  public void byeHelloService() {
    LOG.info("<<< HelloService stepping down!");
  }

}
