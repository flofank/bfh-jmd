package ch.bfh.fankf4.jmd.persistence.relationships.model;

import javax.persistence.*;

@Entity
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String phonenumber;

  @ManyToOne(optional = false)
  private Employee employee;

  @Enumerated(EnumType.STRING)
  private PhoneType type;

  public Phone() {
  }

  public Phone(String phonenumber, PhoneType type) {
    this.phonenumber = phonenumber;
    this.type = type;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public PhoneType getType() {
    return type;
  }

  public void setType(PhoneType type) {
    this.type = type;
  }
}
