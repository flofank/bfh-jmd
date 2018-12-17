package ch.bfh.fankf4.jmd.appservices.hello.async;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeService {

  @Scheduled(fixedRate = 10000)
  public void displayTime() {
    System.out.println("it's " + LocalDateTime.now());
  }
}
