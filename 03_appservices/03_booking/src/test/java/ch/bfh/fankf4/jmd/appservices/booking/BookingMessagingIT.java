package org.example;

import ch.bfh.fankf4.jmd.appservices.booking.BookingService;
import ch.bfh.fankf4.jmd.appservices.booking.LoggingService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingMessagingIT {

  @Autowired
  private BookingService bookingService;
  @Autowired
  private LoggingService loggingService;
  /*
  @Autowired
  private JmsTemplate jmsTemplate;
  @Value("${destination}")
  private String destination;

  @Test
  public void bookEvent() throws InterruptedException {
    jmsTemplate.convertAndSend(destination, "Concert:John");
    Thread.sleep(1000);
    assertThat(bookingService.getBookings("Concert")).contains("John");
    assertThat(loggingService.getMessages()).contains("Concert:John");
  }

  @Test
  public void bookEventFailure() throws InterruptedException {
    jmsTemplate.convertAndSend(destination, "Theatre:Jonathon");
    Thread.sleep(10000);
    assertThat(bookingService.getBookings("Theatre")).doesNotContain("Jonathon");
    assertThat(loggingService.getMessages()).contains("Theatre:Jonathon");
    jmsTemplate.setReceiveTimeout(1000);
    assertThat(jmsTemplate.receive("ActiveMQ.DLQ")).isNotNull();
  }
  */
}