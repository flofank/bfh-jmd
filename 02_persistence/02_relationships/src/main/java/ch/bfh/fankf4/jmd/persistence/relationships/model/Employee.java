package ch.bfh.fankf4.jmd.persistence.relationships.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private Long salary;

  @ManyToOne(optional = true)
  private Employee boss;

  @OneToMany(mappedBy = "boss")
  private List<Employee> employees;

  @OneToMany(mappedBy = "employee")
  private List<Phone> phones;

  @ManyToOne
  private Department department;

  @OneToOne
  private Address address;

  @ManyToMany(mappedBy = "employees")
  private List<Project> projects;

  public Employee() {
  }

  public Employee(String name) {
    this.name = name;
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

  public Long getSalary() {
    return salary;
  }

  public void setSalary(Long salary) {
    this.salary = salary;
  }

  public Employee getBoss() {
    return boss;
  }

  public void setBoss(Employee boss) {
    this.boss = boss;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public List<Phone> getPhones() {
    return phones;
  }

  public void setPhones(List<Phone> phones) {
    this.phones = phones;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public void addProject(Project p) {
    if (projects == null) {
      projects= new ArrayList<>();
    }
    if (!projects.contains(p)) {
      projects.add(p);
      p.addEmployee(this);
    }
  }
}
