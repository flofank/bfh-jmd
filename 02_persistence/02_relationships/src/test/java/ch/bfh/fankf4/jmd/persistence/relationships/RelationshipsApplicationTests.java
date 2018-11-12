package ch.bfh.fankf4.jmd.persistence.relationships;

import ch.bfh.fankf4.jmd.persistence.relationships.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class RelationshipsApplicationTests {

  @Autowired
  private EntityManager entityManager;

  @Test
  @Commit
  public void test() {
    // Setup departments
    Department it = new Department("IT");
    entityManager.persist(it);

    // Setup Projets
    Project p1 = new Project("Project 1");
    Project p2 = new Project("Project 2");
    entityManager.persist(p1);
    entityManager.persist(p2);

    // Create Address
    Address a = new Address("Bahnhofstrasse", "Aarau", "AG", "5000");
    entityManager.persist(a);

    // Create Boss and Worker
    Employee boss = new Employee("Boss");
    boss.setAddress(a);
    boss.setDepartment(it);
    entityManager.persist(boss);
    Employee worker = new Employee("Worker");
    worker.setBoss(boss);
    worker.setDepartment(it);
    entityManager.persist(worker);

    // Add them to projects
    entityManager.refresh(p1);
    p1.getEmployees().add(boss);
    p1.getEmployees().add(worker);
    entityManager.persist(p1);
    entityManager.refresh(p2);
    p2.getEmployees().add(worker);
    entityManager.persist(p2);
    entityManager.flush();

    // Setup Phones
    Phone mobile = new Phone("0791234567", PhoneType.MOBILE);
    mobile.setEmployee(worker);
    entityManager.persist(mobile);
    Phone landline = new Phone("0621234567", PhoneType.LANDLINE);
    landline.setEmployee(boss);
    entityManager.persist(landline);

    // Check those relations
    entityManager.refresh(worker);
    Assert.assertEquals(mobile, worker.getPhones().get(0));
    entityManager.refresh(boss);
    Assert.assertTrue(boss.getPhones().contains(landline));
    Assert.assertEquals(1, boss.getEmployees().size());
    entityManager.refresh(p1);
    Assert.assertEquals(2, p1.getEmployees().size());
    entityManager.refresh(p2);
    Assert.assertEquals(1, p2.getEmployees().size());

  }

}

