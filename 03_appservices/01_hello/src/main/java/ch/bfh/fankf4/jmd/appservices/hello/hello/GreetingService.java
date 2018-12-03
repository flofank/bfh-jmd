package ch.bfh.fankf4.jmd.appservices.hello.hello;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

  public String getDayTimeGreeting(int hour) {
    if (hour > 20) {
      return "Good night";
    } else if (hour > 15) {
      return "Good evening";
    } else if (hour > 9) {
      return "Hello";
    } else if (hour > 4) {
      return "Good morning";
    } else {
      return "Go to sleep";
    }
  }
}
