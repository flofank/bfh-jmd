package ch.bfh.fankf4.jmd.architecture.springboot;

import ch.bfh.fankf4.jmd.architecture.springboot.components.CountryCodeParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

  @Autowired
  private CountryCodeParser m_parser;

  @Test
  public void simpleParserTest() {
    testParser(m_parser);
  }


  private void testParser(CountryCodeParser parser) {
    Assert.assertEquals("Schweiz", parser.parseCode("ch"));
    Assert.assertEquals("Deutschland", parser.parseCode("de"));
    Assert.assertNotEquals("Schweiz", parser.parseCode("gb"));
  }

}
