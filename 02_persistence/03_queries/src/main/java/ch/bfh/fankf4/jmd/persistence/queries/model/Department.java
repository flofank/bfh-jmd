package ch.bfh.fankf4.jmd.persistence.queries.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQuery(name = Department.AVG_SALARY, query = "select new ch.bfh.fankf4.jmd.persistence.queries.model.DepartmentSalaryStatistics(e.department.name, avg(e.salary)) from Employee e group by e.department.name")
@Entity
public class Department {

    public static final String AVG_SALARY = "avgSalary";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq")
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    public Department() {
    }

    public Department(String name) {
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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
