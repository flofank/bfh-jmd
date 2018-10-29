package ch.bfh.fankf4.jmd.persistence.hr;

import ch.bfh.fankf4.jmd.persistence.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HrApplicationRepository extends JpaRepository<Employee, Integer> {

  //@Query("SELECT e FROM Employee e where e.m_name = :name")
  //List<Employee> findAllEmployees(String name);

  List<Employee> findAllByNameLike(String name);
}
