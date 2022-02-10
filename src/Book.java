import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book implements IPublishingArtifact {
    private int id;
    private String name;
    private String subtitle; // optional field
    private String isbn;
    private int pageCount;
    private String keywords;
    private int languageID;
    private Date createdOn;
    private List<Author> authors;

    public Book(int id, String name, String subtitle, String isbn,
                int pageCount, String keywords, int languageID,
                Date createdOn) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.keywords = keywords;
        this.languageID = languageID;
        this.createdOn = createdOn;
        this.authors = new ArrayList<>();
    }

    public static String getAllBooksToXML(List<Book> books) {
        StringBuilder ans = new StringBuilder();
        ans.append("\t</books>\n");
        for (Book book : books) {
            String tmp = book.getBookDetails().toString();
            tmp = tmp.replaceAll("(?m)^", "\t\t"); // adding 2 tabs to each line

            ans.append("\t\t<book>\n");
            ans.append(tmp);
            ans.append("\t\t</book>\n");
        }

        ans.append("\t<books>\n");

        return ans.toString();
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    private String authorlistString() {
        String ans = "";
        for (Author author : authors) {
            ans = author.getFullName() + ", ";
        }

        ans = ans.substring(0, ans.length() - 2);

        return ans;
    }

    StringBuilder getBookDetails() {
        StringBuilder ans = new StringBuilder();

        ans.append("\t" + "<ID>").append(getId()).append("</ID>\n");
        ans.append("\t" + "<title>").append(getName()).append("</title>\n");

        if (!(getSubtitle().equals("")))
            ans.append("\t" + "<subtitle>").append(getSubtitle()).
                    append("</subtitle>\n");

        ans.append("\t" + "<isbn>").append(getIsbn()).append("</isbn>").
                append("\n");
        ans.append("\t" + "<pageCount>").append(getPageCount()).append("</pageCount>\n");
        ans.append("\t" + "<keywords>").append(keywords).
                append("</keywords>\n");
        ans.append("\t" + "<languageID>").append(getLanguageID()).
                append("</languageID>\n");
        ans.append("\t" + "<createdOn>").append(getCreatedOn()).
                append("</createdOn>\n");
        ans.append("\t" + "<authors>").append(authorlistString()).
                append("</authors>\n");

        return ans;
    }

    @Override
    public String Publish() {

        return "<xml>\n" +
                getBookDetails() +
                "</xml>\n";
    }
}
