package ch.bfh.jmd.architecture;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CountryCodeParserInvocationHandler implements InvocationHandler {
    private static final CountryCodeParserFactory FACTORY = new CountryCodeParserFactory();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(FACTORY.getRemoteCountryCodeParser(), args);
    }
}
