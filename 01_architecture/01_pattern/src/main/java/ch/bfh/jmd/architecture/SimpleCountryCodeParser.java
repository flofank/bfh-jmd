package ch.bfh.jmd.architecture;

import java.util.Locale;

public class SimpleCountryCodeParser implements CountryCodeParser {


    @Override
    public String parseCode(String code) {
        return new Locale("", code).getDisplayCountry();
    }
}
