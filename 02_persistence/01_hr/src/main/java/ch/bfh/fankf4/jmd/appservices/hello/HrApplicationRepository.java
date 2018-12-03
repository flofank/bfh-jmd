package ch.bfh.fankf4.jmd.appservices.hello;

import ch.bfh.fankf4.jmd.appservices.hello.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HrApplicationRepository extends JpaRepository<Employee, Integer> {

  //@Query("SELECT e FROM Employee e where e.m_name = :name")
  //List<Employee> findAllEmployees(String name);

  List<Employee> findAllByNameLike(String name);
}
