package ch.bfh.fankf4.jmd.appservices.booking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Booking {

  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 5)
  private String customer;

  private String event;

  public Booking() {}

  public Booking(String event, String customer) {
    this.event = event;
    this.customer = customer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }
}
