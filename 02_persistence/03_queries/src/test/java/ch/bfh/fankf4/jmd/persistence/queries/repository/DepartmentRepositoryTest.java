package ch.bfh.fankf4.jmd.persistence.queries.repository;

import ch.bfh.fankf4.jmd.persistence.queries.model.Department;
import ch.bfh.fankf4.jmd.persistence.queries.model.DepartmentDTO;
import ch.bfh.fankf4.jmd.persistence.queries.model.DepartmentNames;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void findAllDepartmentDTO() {
        List<DepartmentDTO> list = departmentRepository.findAllDepartmentDTO();

        assertEquals(2, list.size());
        assertEquals(5L, (long) list.get(0).getNumberOfEmployees());
    }

    @Test
    public void findAllDepartmentNames() {
        List<DepartmentNames> list = departmentRepository.findAllDepartmentNames();

        assertEquals(2, list.size());
    }

    @Test
    public void findAllOrdered() {
        List<Department> departments = departmentRepository.findAll();
    }
}