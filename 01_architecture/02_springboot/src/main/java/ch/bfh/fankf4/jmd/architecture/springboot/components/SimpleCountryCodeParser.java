package ch.bfh.fankf4.jmd.architecture.springboot.components;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Profile("!prod")
public class SimpleCountryCodeParser implements CountryCodeParser {
  private static final Logger LOG = LoggerFactory.getLogger(SimpleCountryCodeParser.class);
  @Override
   public String parseCode(String code) {
    LOG.info("Parsing {}", code);
    return new Locale("", code).getDisplayCountry();
  }
}
