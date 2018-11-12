package ch.bfh.fankf4.jmd.persistence.queries.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {

    private final EntityManager em;

    public CustomEmployeeRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void refresh(Object object) {
        em.refresh(object);
    }
}
