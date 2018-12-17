package ch.bfh.fankf4.jmd.appservices.booking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Payment {

  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 5)
  private String customer;

  @Column(length = 16)
  private String creditCard;

  public Payment() {}

  public Payment(String creditCard, String customer) {
    this.creditCard = creditCard;
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
    return creditCard;
  }

  public void setEvent(String creditCard) {
    this.creditCard = creditCard;
  }
}
