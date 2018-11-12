package ch.bfh.fankf4.jmd.persistence.queries.repository;

import ch.bfh.fankf4.jmd.persistence.queries.model.Department;
import ch.bfh.fankf4.jmd.persistence.queries.model.DepartmentDTO;
import ch.bfh.fankf4.jmd.persistence.queries.model.DepartmentNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("select new ch.bfh.fankf4.jmd.persistence.queries.model.DepartmentDTO(d.name, count(e)) from Department d join d.employees e group by d.name")
    List<DepartmentDTO> findAllDepartmentDTO();

    @Query("select d.name from Department d join d.employees e group by d.name")
    List<DepartmentNames> findAllDepartmentNames();
}
