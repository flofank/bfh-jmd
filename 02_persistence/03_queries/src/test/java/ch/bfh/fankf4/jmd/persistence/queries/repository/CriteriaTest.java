package ch.bfh.fankf4.jmd.persistence.queries.repository;

import ch.bfh.fankf4.jmd.persistence.queries.model.*;
import org.hibernate.type.EntityType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class CriteriaTest {

  @Autowired
  private EntityManager em;

  /**
   * Finden Sie alle Mitarbeiter die im Kanton Zürich wohnen
   */
  @Test
  public void findAllZuercher() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
    Root<Employee> employee = cq.from(Employee.class);
    cq.select(employee);
    cq.where(cb.equal(employee.get(Employee_.address).get(Address_.state), "ZH"));
    //cq.where(employee.get(Employee_.address).get(Address_.state).in("ZH"));

    TypedQuery<Employee> query = em.createQuery(cq);
    List<Employee> zuercher = query.getResultList();

    assertEquals(3, zuercher.size());
  }

  /**
   * Berechnen Sie das Durchschnittsgehalt der Employees pro Abteilung
   */
  @Test
  public void getAverageSalaryPerDepartment() {
    // select new DepartmentSalaryStatistics(e.department.name, avg(e.salary)) from Employee e group by e.department.name
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<DepartmentSalaryStatistics> cq = cb.createQuery(DepartmentSalaryStatistics.class);
    Root<Employee> employee = cq.from(Employee.class);
    cq.groupBy(employee.get(Employee_.department).get("name"));
    cq.select(cb.construct(DepartmentSalaryStatistics.class, employee.get(Employee_.department).get("name"),
        cb.avg(employee.get(Employee_.salary))));

    TypedQuery<DepartmentSalaryStatistics> query = em.createQuery(cq);
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
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
    Root<Employee> employee = cq.from(Employee.class);
    Subquery<Long> sub = cq.subquery(Long.class);
    Root<Employee> sEmployee = sub.from(Employee.class);
    sub.select(cb.min(sEmployee.get(Employee_.salary)));
    cq.where(cb.equal(employee.get(Employee_.salary), sub));


    TypedQuery<Employee> query = em.createQuery(cq);
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
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
    Root<Employee> employee = cq.from(Employee.class);
    cq.where(cb.isEmpty(employee.get(Employee_.projects)));

    TypedQuery<Employee> query = em.createQuery(cq);
    List<Employee> list = query.getResultList();

    assertEquals(3, list.size());
  }

  /**
   * Finden Sie alle Geschäftstelefonnummern sortiert nach Nummer
   */
  @Test
  public void findAllWorkPhonesOrderedByNumber() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<String> cq = cb.createQuery(String.class);
    Root<Phone> phone = cq.from(Phone.class);
    cq.select(phone.get(Phone_.phonenumber));
    cq.where(cb.equal(phone.get(Phone_.type), PhoneType.WORK));

    TypedQuery<String> query = em.createQuery(cq);
    List<String> list = query.getResultList();

    assertEquals(5, list.size());
  }


  /**
   * Finden Sie Mitarbeiter die noch keine Geschäftstelefonnummer haben
   */
  @Test
  public void findAllEmployeesWithoutWorkPhone() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
    Root<Employee> employee = cq.from(Employee.class);

    Subquery<Employee> sub = cq.subquery(Employee.class);
    Root<Employee> sEmployee = sub.from(Employee.class);
    Join<Employee, Phone> sPhone = sEmployee.join(Employee_.phones);
    sub.where(cb.equal(sPhone.get(Phone_.type), PhoneType.WORK));
    sub.select(sEmployee);
    sub.distinct(true);
    cq.where(cb.not(employee.in(sub)));


    TypedQuery<Employee> query = em.createQuery(cq);
    List<Employee> list = query.getResultList();

    assertEquals(2, list.size());
  }
}
