package ch.bfh.fankf4.jmd.appservices.booking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingIT {

  @Autowired
  private BookingService bookingService;

  @Test
  public void bookEvent() {
    try {
      bookingService.bookEvent("Concert", "Alice", "Bob", "Carol");
    } catch (RuntimeException ex) {
      ex.printStackTrace();
    }
    assertThat(bookingService.getBookings("Concert")).contains("Alice", "Bob", "Carol");
  }

  @Test
  public void bookEventFailure() {
    try {
      bookingService.bookEvent("Theatre", "Alice", "Bob", "Charles");
    } catch (RuntimeException ex) {
      ex.printStackTrace();
    }
    assertThat(bookingService.getBookings("Theatre")).doesNotContain("Alice", "Bob", "Carol");
  }
}