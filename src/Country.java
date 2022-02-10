import java.util.List;

public class Country {
    private int id;
    private String countryCode;

    public Country(int id, String countryCode) {
        this.id = id;
        this.countryCode = countryCode;
    }

    public static String getAllCountries(List<Country> countries) {
        StringBuilder ans = new StringBuilder();
        for (Country country : countries)
            ans.append(country.getCountryCode()).append(" ");

        ans.append('\n');
        return ans.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    @SuppressWarnings("unused")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
