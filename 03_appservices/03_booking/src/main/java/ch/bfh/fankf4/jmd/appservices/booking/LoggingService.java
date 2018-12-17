package ch.bfh.fankf4.jmd.appservices.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoggingService {

  @Autowired
  private LoggingRepository repo;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void log(String message) {
    Logging log = new Logging(message);
    repo.save(log);
  }

  public List<String> getMessages() {
    return repo.getAllMessages();
  }
}
