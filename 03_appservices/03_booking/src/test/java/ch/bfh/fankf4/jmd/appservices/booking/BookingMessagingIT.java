package ch.bfh.fankf4.jmd.appservices.booking;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingMessagingIT {

  @Autowired
  private BookingService bookingService;
  @Autowired
  private LoggingService loggingService;
  @Autowired
  private JmsTemplate jmsTemplate;
  @Value("${destination}")
  private String destination;

  @Before
  public void init() {
    jmsTemplate.setReceiveTimeout(1000);
  }

  @Test
  public void bookEvent() throws InterruptedException {
    jmsTemplate.convertAndSend(destination, "Concert:John");
    Thread.sleep(1000);
    assertThat(bookingService.getBookings("Concert")).contains("John");
    assertThat(loggingService.getMessages()).contains("Concert:John");
    assertThat(jmsTemplate.receive(destination)).isNull();
  }

  @Test
  public void bookEventFailure() throws InterruptedException {
    jmsTemplate.convertAndSend(destination, "Theatre:Jonathon");
    Thread.sleep(10000);
    assertThat(bookingService.getBookings("Theatre")).doesNotContain("Jonathon");
    assertThat(loggingService.getMessages()).contains("Theatre:Jonathon");
    assertThat(jmsTemplate.receive("ActiveMQ.DLQ")).isNotNull();
  }
}