package ch.bfh.jmd.architecture;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class RemoteCountryCodeParser implements CountryCodeParser {
    private static final String BASE_URL = "http://jmd-translation.eu-central-1.elasticbeanstalk.com/country/";
    //private static final String BASE_URL = "http://jmd-translation.eu-central-1.elasticbeanstalk.com/language/";

    @Override
    public String parseCode(String code) {
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
