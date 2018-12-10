package ch.bfh.fankf4.jmd.appservices.hello.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

  @Autowired
  private GreetingConfiguration config;

  public String getDayTimeGreeting(int hour) {
    int h = hour;
    while (h > 0) {
      if (config.getDaytimePhrases().containsKey(h)) {
        return config.getDaytimePhrases().get(h);
      }
      h--;
    }
    throw new IllegalStateException("No Greeting phrase configured for " + hour + "h");
  }
}
