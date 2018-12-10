package ch.bfh.fankf4.jmd.appservices.hello.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class HelloCommandLineRunner implements CommandLineRunner {
  private static final Logger LOG = LoggerFactory.getLogger(HelloCommandLineRunner.class);
  private final HelloService helloService;


  public HelloCommandLineRunner(HelloService helloService) {
    this.helloService = helloService;
  }

  @Override
  public void run(String... args) {
    while(true) {
      LOG.info(helloService.sayHello("Peter", 21));
      LOG.info(helloService.sayHello("Max", 17));
      LOG.info("Sleeping ------");
      try {
        Thread.sleep(1000);
      } catch (Exception e) {}
    }
  }
}
