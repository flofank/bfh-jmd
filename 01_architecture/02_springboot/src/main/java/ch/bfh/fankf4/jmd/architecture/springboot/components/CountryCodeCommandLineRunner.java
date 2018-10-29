package ch.bfh.fankf4.jmd.architecture.springboot.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CountryCodeCommandLineRunner implements CommandLineRunner {
  private static final Logger LOG = LoggerFactory.getLogger(CountryCodeCommandLineRunner.class);

  private final CountryCodeParser m_parser;

  public CountryCodeCommandLineRunner(CountryCodeParser parser) {
    m_parser = parser;
  }

  @Override
  public void run(String... args) {
    LOG.info("Parsing CH to {}", m_parser.parseCode("CH"));
  }
}
