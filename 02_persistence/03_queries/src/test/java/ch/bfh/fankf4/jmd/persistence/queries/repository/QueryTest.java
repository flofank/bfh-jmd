package ch.bfh.fankf4.jmd.persistence.queries.repository;

import ch.bfh.fankf4.jmd.persistence.queries.model.Department;
import ch.bfh.fankf4.jmd.persistence.queries.model.DepartmentSalaryStatistics;
import ch.bfh.fankf4.jmd.persistence.queries.model.Employee;
import ch.bfh.fankf4.jmd.persistence.queries.model.PhoneType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class QueryTest {

    @Autowired
    private EntityManager em;

    /**
     * Finden Sie alle Mitarbeiter die im Kanton Zürich wohnen
     */
    @Test
    public void findAllZuercher() {
        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where e.address.state = 'ZH'", Employee.class);
        List<Employee> zuercher = query.getResultList();

        assertEquals(3, zuercher.size());
    }

    /**
     * Berechnen Sie das Durchschnittsgehalt der Employees pro Abteilung
     */
    @Test
    public void getAverageSalaryPerDepartment() {
        TypedQuery<DepartmentSalaryStatistics> query = em.createNamedQuery(
                Department.AVG_SALARY, DepartmentSalaryStatistics.class);
        List<DepartmentSalaryStatistics> list = query.getResultList();

        assertEquals(2, list.size());

        assertEquals("IT", list.get(0).getDepartmentName());
        assertEquals(97200.0, list.get(0).getAvgSalary(), 0);
        assertEquals("HR", list.get(1).getDepartmentName());
        assertEquals(95000.0, list.get(1).getAvgSalary(), 0);
    }

    /**
     * Finden Sie den Mitarbeiter mit dem kleinsten Gehalt (Tipp: Es führen viele Wege nach Rom...)
     */
    @Test
    public void findEmployeeWithSmallestSalary() {
        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where e.salary = (select min(e.salary) from Employee e)", Employee.class);
        List<Employee> list = query.getResultList();

        assertEquals(2, list.size());

        assertEquals("Luca Traugott", list.get(0).getName());
        assertEquals("Lea Schulze", list.get(1).getName());
    }

    /**
     * Erstellen Sie eine Abfrage die den Mitarbeiternamen und die komplette Adresse zurückgibt. Sortiert nach Mitarbeitername.
     */
    @Test
    public void findAllEmployeeNameWithAddress() {
        Query query = em.createQuery("select e.name, e.address from Employee e");
        List<Object[]> list = query.getResultList();

        assertEquals(6, list.size());
    }

    /**
     * Finden Sie Mitarbeiter die keinem Projekt zugeordnet sind
     */
    @Test
    public void findAllEmployeesWithoutProject() {
        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where e.projects is empty", Employee.class);
        List<Employee> list = query.getResultList();

        assertEquals(3, list.size());
    }

    /**
     * Finden Sie alle Geschäftstelefonnummern sortiert nach Nummer
     */
    @Test
    public void findAllWorkPhonesOrderedByNumber() {
        TypedQuery<String> query = em.createQuery(
                "select p.phonenumber from Phone p where p.type = :type order by p.type", String.class);
        query.setParameter("type", PhoneType.WORK);
        List<String> list = query.getResultList();

        assertEquals(5, list.size());
    }


    /**
     * Finden Sie Mitarbeiter die noch keine Geschäftstelefonnummer haben
     */
    @Test
    public void findAllEmployeesWithoutWorkPhone() {
        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where e not in (select distinct e from Employee e join e.phones p where p.type = :type)", Employee.class);
        query.setParameter("type", PhoneType.WORK);
        List<Employee> list = query.getResultList();

        assertEquals(2, list.size());
    }

    @Test
    public void findAllEmployeesWithoutWorkPhone2() {
        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where not exists (select p from Phone p where p.employee = e and p.type = :type)", Employee.class);
        query.setParameter("type", PhoneType.WORK);
        List<Employee> list = query.getResultList();

        assertEquals(2, list.size());
    }
}
