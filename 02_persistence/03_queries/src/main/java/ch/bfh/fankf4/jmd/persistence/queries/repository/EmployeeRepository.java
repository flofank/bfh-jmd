package ch.bfh.fankf4.jmd.persistence.queries.repository;

import ch.bfh.fankf4.jmd.persistence.queries.model.Employee;
import ch.bfh.fankf4.jmd.persistence.queries.model.EmployeeNameWithAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, CustomEmployeeRepository {

    List<Employee> findAllByNameLike(String name);

    @Query(value = "select e.* from Employee e where e.name like :name", nativeQuery = true)
    List<Employee> findAllByNameLikeWithQuery(@Param("name") String name);

    List<Employee> findAllByAddressState(String state);

    @Query("select e.name, e.address from Employee e")
    List<EmployeeNameWithAddress> findAllWithNameAndAddress();
}
