package ch.bfh.fankf4.jmd.architecture.springboot.components;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class CountryCodeParserFactory {
    private static final String IMPLEMENTATION = "ch.bfh.fankf4.jmd.architecture.springboot.components.RemoteCountryCodeParser";

    @Bean
    public CountryCodeParser getCountryCodeParser() {
        try {
            return (CountryCodeParser) Class.forName(IMPLEMENTATION).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CountryCodeParser getSimpleCountryCodeParser() {
        return new SimpleCountryCodeParser();
    }

    public CountryCodeParser getRemoteCountryCodeParser() {
        return new RemoteCountryCodeParser();
    }

}
