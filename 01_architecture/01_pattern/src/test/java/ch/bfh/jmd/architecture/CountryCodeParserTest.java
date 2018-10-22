package ch.bfh.jmd.architecture;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class CountryCodeParserTest {

    @Test
    public void simpleParserTest() {
        CountryCodeParser parser = new SimpleCountryCodeParser();
        testParser(parser);
    }

    @Test
    public void remoteParserTest() {
        CountryCodeParser parser = new RemoteCountryCodeParser();
        testParser(parser);
    }

    @Test
    public void testFactory() {
        CountryCodeParserFactory factory = new CountryCodeParserFactory();
        testParser(factory.getCountryCodeParser());
    }

    @Test
    public void testDynamicProxy() {
        CountryCodeParser parser = (CountryCodeParser) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] {CountryCodeParser.class}, new CountryCodeParserInvocationHandler());
        testParser(parser);
    }

    private void testParser(CountryCodeParser parser) {
        Assert.assertEquals("Schweiz", parser.parseCode("ch"));
        Assert.assertEquals("Deutschland", parser.parseCode("de"));
        Assert.assertNotEquals("Schweiz", parser.parseCode("gb"));
    }
}