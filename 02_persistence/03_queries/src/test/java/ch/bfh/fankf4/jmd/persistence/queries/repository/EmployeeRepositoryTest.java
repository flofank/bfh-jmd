package ch.bfh.fankf4.jmd.persistence.queries.repository;

import ch.bfh.fankf4.jmd.persistence.queries.model.Address;
import ch.bfh.fankf4.jmd.persistence.queries.model.Employee;
import ch.bfh.fankf4.jmd.persistence.queries.model.EmployeeNameWithAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void save() {
        Employee employee = new Employee("Hans Gfeller", 70_000L);
        Address address = new Address("Bahnhofstrasse 1", "Zürich", "ZH", "8000");
        employee.setAddress(address);

        Employee savedEmployee = employeeRepository.saveAndFlush(employee);

        assertNotNull(savedEmployee.getId());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void refresh() {
        employeeRepository.refresh(new Employee());
    }

    @Test
    public void findAllByNameLike() {
        List<Employee> list = employeeRepository.findAllByNameLike("P%");

        assertEquals(1, list.size());
        assertEquals("Peter Muster", list.get(0).getName());

        list.get(0).getAddress().getCity();
    }

    @Test
    public void findAllByNameLikeWithQuery() {
        List<Employee> list = employeeRepository.findAllByNameLikeWithQuery("P%");

        assertEquals(1, list.size());
        assertEquals("Peter Muster", list.get(0).getName());

        list.get(0).getAddress().getCity();
    }

    @Test
    public void findAllByAddressState() {
        List<Employee> zuercher = employeeRepository.findAllByAddressState("ZH");

        assertEquals(3, zuercher.size());
    }

    @Test
    public void findAllWithNameAndAddress() {
        List<EmployeeNameWithAddress> list = employeeRepository.findAllWithNameAndAddress();

        assertEquals(6, list.size());
    }
}