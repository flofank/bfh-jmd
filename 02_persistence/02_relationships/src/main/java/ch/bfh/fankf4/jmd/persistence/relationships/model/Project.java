package ch.bfh.fankf4.jmd.persistence.relationships.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @ManyToMany
  private List<Employee> employees;

  public Project() {
  }

  public Project(String name) {
    this.name = name;
  }

  public void addEmployee(Employee e) {
    if (employees == null) {
      employees = new ArrayList<>();
    }
    if (!employees.contains(e)) {
      employees.add(e);
      e.addProject(this);
    }
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }
}
