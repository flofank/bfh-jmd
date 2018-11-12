package ch.bfh.fankf4.jmd.persistence.queries.repository;

import ch.bfh.fankf4.jmd.persistence.queries.model.Employee;
import ch.bfh.fankf4.jmd.persistence.queries.testdata.TestDataGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(showSql = false)
@RunWith(SpringRunner.class)
public class EntityManagerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerIT.class);

    @Autowired
    private EntityManager em;

    private final TestDataGenerator testDataGenerator = new TestDataGenerator();

    private Integer peterMusterId;

    @Test
    public void findPeterMuster() {
        LOGGER.info(">>>>> findPeterMuster()");

        Employee peterMuster = em.find(Employee.class, peterMusterId);

        Assert.assertNotNull(peterMuster);
        Assert.assertEquals(1, peterMuster.getPhones().size());

        LOGGER.info("<<<<< findPeterMuster()");
    }

    @Test
    public void findPeterMusterWithEntityGraph() {
        LOGGER.info(">>>>> findPeterMusterWithEntityGraph()");

        EntityGraph graphPhones = em.getEntityGraph("graph.Employee.phones");
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graphPhones);

        Employee peterMuster = em.find(Employee.class, peterMusterId, hints);

        Assert.assertNotNull(peterMuster);
        Assert.assertEquals(1, peterMuster.getPhones().size());

        LOGGER.info("<<<<< findPeterMusterWithEntityGraph()");
    }

    @Commit
    @Test
    public void removeAddress() {
        LOGGER.info(">>>>> removeAddress()");

        Employee peterMuster = em.find(Employee.class, peterMusterId);

        Assert.assertNotNull(peterMuster);

        peterMuster.setAddress(null);

        em.flush();

        LOGGER.info("<<<<< removeAddress()");
    }

    @Before
    public void createTestData() {
        peterMusterId = testDataGenerator.createEmployee(em);
        em.clear();
    }
}
