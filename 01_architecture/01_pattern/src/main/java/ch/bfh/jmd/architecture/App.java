package ch.bfh.jmd.architecture;

public class App {
    public static void main(String[] args) {
        CountryCodeParser parser = new SimpleCountryCodeParser();
        System.out.println("CH -> " + parser.parseCode("ch"));
    }
}
