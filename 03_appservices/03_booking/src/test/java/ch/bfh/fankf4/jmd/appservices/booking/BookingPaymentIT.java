package ch.bfh.fankf4.jmd.appservices.booking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingPaymentIT {

  @Autowired
  private BookingService bookingService;

  @Autowired
  private LoggingService loggingService;


  @Test
  public void bookEvent() {
    try {
      bookingService.bookEventAndPay("Concert", "Peter", "4000000000000000");
    } catch (RuntimeException ex) {
    }
    assertThat(bookingService.getBookings("Concert")).contains("Peter");
    assertThat(loggingService.getMessages()).contains("Concert:Peter:4000000000000000");
  }

  @Test
  public void bookEventFailure() {
    try {
      bookingService.bookEventAndPay("Theatre", "Peter", "40000000000000000");
    } catch (RuntimeException ex) {
    }
    assertThat(bookingService.getBookings("Theatre")).doesNotContain("Peter");
    assertThat(loggingService.getMessages()).contains("Theatre:Peter:40000000000000000");
  }

}