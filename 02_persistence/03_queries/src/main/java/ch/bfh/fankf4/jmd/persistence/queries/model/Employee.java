package ch.bfh.fankf4.jmd.persistence.queries.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.Employee.addressAndDepartment",
                attributeNodes = {
                        @NamedAttributeNode("address"),
                        @NamedAttributeNode("department")
                }),
        @NamedEntityGraph(name = "graph.Employee.phones",
                attributeNodes = {
                        @NamedAttributeNode("phones")
                })
})
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq")
    private Integer id;

    private String name;
    private long salary;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee boss;

    @OneToMany(mappedBy = "boss", cascade = CascadeType.REFRESH)
    private Set<Employee> directs = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @ManyToMany(cascade = CascadeType.REFRESH)
    private Set<Project> projects = new HashSet<>();

    public Employee() {
    }

    public Employee(String name, long salary) {
        this.name = name;
        this.salary = salary;
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

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public Set<Employee> getDirects() {
        return directs;
    }

    public void setDirects(Set<Employee> directs) {
        this.directs = directs;
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

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addDirect(Employee employee) {
        employee.setBoss(this);
        directs.add(employee);
    }

    public void removeDirect(Employee employee) {
        employee.setBoss(null);
        directs.remove(employee);
    }

    public void addPhone(Phone phone) {
        phone.setEmployee(this);
        phones.add(phone);
    }

    public void removePhone(Phone phone) {
        phone.setEmployee(null);
        phones.remove(phone);
    }
}
