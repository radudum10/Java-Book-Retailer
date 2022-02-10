import java.util.ArrayList;
import java.util.List;

public class PublishingRetailer {
    private int id;
    private String name;
    private List<IPublishingArtifact> publishingArtifacts;
    private List<Country> countries;

    public PublishingRetailer(int id, String name) {
        this.id = id;
        this.name = name;
        this.publishingArtifacts = new ArrayList<>();
        this.countries = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IPublishingArtifact> getPublishingArtifacts() {
        return publishingArtifacts;
    }

    public void setPublishingArtifacts(List<IPublishingArtifact> publishingArtifacts) {
        this.publishingArtifacts = publishingArtifacts;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void addCountry(Country country) {
        this.countries.add(country);
    }

    public void addPublishingArtifact(IPublishingArtifact artifact) {
        this.publishingArtifacts.add(artifact);
    }
}
