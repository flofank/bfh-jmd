package ch.bfh.fankf4.jmd.appservices.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

  @Autowired
  private BookingRepository repo;

  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private LoggingService loggingService;

  @Transactional
  public void bookEvent(String concert, String... customers) {
    for (String customer : customers) {
      Booking b = new Booking(concert, customer);
      loggingService.log(concert + ":" + customer);
      repo.save(b);
    }
  }

  public List<String> getBookings(String concert) {
    List<String> customers = new ArrayList<>();
    for (Booking b : repo.findAllByEvent(concert)) {
      customers.add(b.getCustomer());
    }
    return customers;
  }

  @Transactional
  public void bookEventAndPay(String concert, String customer, String creditcard) {
    loggingService.log(concert + ":" + customer + ":" + creditcard);
    Booking b = new Booking(concert, customer);
    repo.save(b);
    payEvent(concert, customer, creditcard);
  }

  private void payEvent(String concert, String customer, String creditcard) {
    paymentRepository.save(new Payment(creditcard, customer));
  }

  @JmsListener(destination = "${destination}")
  public void onHistoryRequest(TextMessage message) throws JMSException {
    String[] booking = message.getText().split(":");
    bookEvent(booking[0], booking[1]);
  }

}
