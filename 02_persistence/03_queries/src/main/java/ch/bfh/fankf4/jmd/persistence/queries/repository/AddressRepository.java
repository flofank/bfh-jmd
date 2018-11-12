package ch.bfh.fankf4.jmd.persistence.queries.repository;

import ch.bfh.fankf4.jmd.persistence.queries.model.Address;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional(readOnly = true)
@Repository
public class AddressRepository {

    private final EntityManager em;

    public AddressRepository(EntityManager em) {
        this.em = em;
    }

    public List<Address> findAll() {
        return em.createQuery("select a from Address a", Address.class).getResultList();
    }

    @Transactional
    public Address save(Address address) {
        if (address.getId() == null) {
            em.persist(address);
            return address;
        } else {
            return em.merge(address);
        }
    }
}
