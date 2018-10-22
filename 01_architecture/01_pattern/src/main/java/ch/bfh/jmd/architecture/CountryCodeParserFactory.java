package ch.bfh.jmd.architecture;

public class CountryCodeParserFactory {
    private static final String IMPLEMENTATION = "ch.bfh.jmd.architecture.SimpleCountryCodeParser";

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
