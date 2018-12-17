package ch.bfh.fankf4.jmd.appservices.booking;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Logging {

  @Id
  private Long id;

  private String message;

  public Logging() {
    id = System.currentTimeMillis();
  }

  public Logging(String message) {
    this();
    this.message = message;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}