package ch.bfh.fankf4.jmd.appservices.hello.hello;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "greetings")
public class GreetingConfiguration {
  private int adultAge;
  private Map<Integer, String> daytimePhrases;

  public int getAdultAge() {
    return adultAge;
  }

  public Map<Integer, String> getDaytimePhrases() {
    return daytimePhrases;
  }

  public void setAdultAge(int adultAge) {
    this.adultAge = adultAge;
  }

  public void setDaytimePhrases(Map<Integer, String> daytimePhrases) {
    this.daytimePhrases = daytimePhrases;
  }
}
