package ch.bfh.fankf4.jmd.appservices.hello.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

  @Id
  private Integer id;
  private String name;
  private Integer m_salary;


  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSalary() {
    return m_salary;
  }

  public void setSalary(Integer salary) {
    m_salary = salary;
  }
}
