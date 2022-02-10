import java.util.ArrayList;
import java.util.List;

public class EditorialGroup implements IPublishingArtifact {
    private int id;
    private String name;
    private List<Book> books;

    public EditorialGroup(int id, String name) {
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
        this.books.add(book);
    }

    String getEditorialGroupDetails() {

        return "\t<editorialGroup>\n" +
                "\t\t<ID>" + getId() + "<ID>\n" +
                "\t\t<Name>" + getName() + "</Name>\n" +
                "\t</editorialGroup>\n";
    }

    @Override
    public String Publish() {

        return "<xml>\n" +
                getEditorialGroupDetails() +
                Book.getAllBooksToXML(books) +
                "</xml>\n";
    }
}
