package ch.bfh.fankf4.jmd.appservices.hello.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Profile("prod")
public class InteractiveHelloCommandLineRunner implements CommandLineRunner {
  private static final Logger LOG = LoggerFactory.getLogger(InteractiveHelloCommandLineRunner.class);
  private final HelloService helloService;


  public InteractiveHelloCommandLineRunner(HelloService helloService) {
    this.helloService = helloService;
  }

  @Override
  public void run(String... args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter your name:");
    String name = scanner.next();
    System.out.println("Please enter your age");
    int age = Integer.parseInt(scanner.next());
    LOG.info(helloService.sayHello(name, age));
  }
}
