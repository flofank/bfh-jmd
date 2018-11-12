package ch.bfh.fankf4.jmd.persistence.queries.testdata;

import ch.bfh.fankf4.jmd.persistence.queries.model.*;

import javax.persistence.EntityManager;

public class TestDataGenerator {

    public Integer createEmployee(EntityManager em) {
        // Department
        Department department = new Department("IT");
        em.persist(department);

        // Project
        Project project = new DesignProject("Website");
        em.persist(project);

        // Boss
        Employee hansBoss = new Employee("Hans Boss", 180_000L);
        em.persist(hansBoss);
        hansBoss.setDepartment(department);

        Address hansAddress = new Address("Marktgasse 1", "Bern", "BE", "3000");
        //em.persist(hansAddress);

        hansBoss.setAddress(hansAddress);

        // Employee
        Employee peterMuster = new Employee("Peter Muster", 80_000L);
        em.persist(peterMuster);

        peterMuster.setDepartment(department);

        peterMuster.getProjects().add(project);
        project.getEmployees().add(peterMuster);

        Address petersAddress = new Address("Spitalgasse 1", "Bern", "BE", "3000");
        //em.persist(petersAddress);

        peterMuster.setAddress(petersAddress);

        Phone phone = new Phone("031 333 11 22", PhoneType.HOME, peterMuster);
        peterMuster.addPhone(phone);
        //em.persist(phone);

        em.flush();

        return peterMuster.getId();
    }
}
