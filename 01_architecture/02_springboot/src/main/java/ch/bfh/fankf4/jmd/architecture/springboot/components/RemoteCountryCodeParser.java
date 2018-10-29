package ch.bfh.fankf4.jmd.architecture.springboot.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class RemoteCountryCodeParser implements CountryCodeParser {
    private static final Logger LOG = LoggerFactory.getLogger(RemoteCountryCodeParser.class);
    private static final String BASE_URL = "http://jmd-translation.eu-central-1.elasticbeanstalk.com/country/";
    //private static final String BASE_URL = "http://jmd-translation.eu-central-1.elasticbeanstalk.com/language/";

    @Override
    public String parseCode(String code) {
        LOG.info("Parsing {}", code);
        try {
            URL url = new URL(BASE_URL + code);
            URLConnection con = url.openConnection();
            return new BufferedReader(new InputStreamReader(con.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
