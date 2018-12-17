package ch.bfh.fankf4.jmd.appservices.booking;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoggingRepository extends JpaRepository<Logging, Long> {

  @Query(value = "Select l.message from Logging l")
  List<String> getAllMessages();
}
