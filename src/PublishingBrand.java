import java.util.ArrayList;
import java.util.List;

public class PublishingBrand implements IPublishingArtifact {
    private int id;
    private String name;
    private List<Book> books;

    public PublishingBrand(int id, String name) {
        this.id = id;
        this.name = name;
        this.books = new ArrayList<>();
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    String getPublishingBrandDetails() {
        String ans = "";
        ans += "\t<publishingBrand>\n";

        ans += "\t\t<ID>" + getId() + "</ID>\n";
        ans += "\t\t<Name>" + getName() + "</Name>\n";

        ans += "\t</publishingBrand>\n";

        return ans;
    }

    @Override
    public String Publish() {

        return "<xml>\n" +
                getPublishingBrandDetails() +
                Book.getAllBooksToXML(books) +
                "</xml>\n";
    }
}
