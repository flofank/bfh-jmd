package ch.bfh.fankf4.jmd.appservices.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class HelloApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelloApplication.class, args);
    System.out.println("##############################################");
    System.out.println("###### Hello Application up and running ######");
    System.out.println("##############################################");
  }

  @Bean
  public ThreadPoolTaskExecutor taskExecutor(){
    ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
    pool.setCorePoolSize(2);
    pool.setMaxPoolSize(2);
    pool.setWaitForTasksToCompleteOnShutdown(true);
    return pool;
  }
}
