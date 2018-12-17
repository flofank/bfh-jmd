package ch.bfh.fankf4.jmd.appservices.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookingService {

  @Autowired
  private BookingRepository repo;

  public void bookEvent(String concert, String... customers) {
    for (String c : customers) {
      Booking b = new Booking(concert, c);
      repo.save(b);
      System.out.println(concert + " - " + c);
    }
  }

  public List<String> getBookings(String concert) {
    List<String> customers = new ArrayList<>();
    for (Booking b : repo.findAllByEvent(concert)) {
      customers.add(b.getCustomer());
    }
    return customers;
  }
}
