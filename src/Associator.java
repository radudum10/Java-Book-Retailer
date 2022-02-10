import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Associator {
    /* all association files have the same template:
    base###associate
     */
    public static <S, T> void baseWithAssoc(HashMap<Integer, S> base,
                                            HashMap<Integer, T> associate,
                                            String path, Class<S> clazzBase,
                                            Class<T> clazzAssociate) {

        File file = new File(path);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skipping the template
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");

                int baseID = Integer.parseInt(tokens[0]);
                int associateID = Integer.parseInt(tokens[1]);

                S baseObj;
                T assocObj;
                /* checking if the keys exist */
                if (associate.containsKey(associateID)) {
                    assocObj = associate.get(associateID);
                } else {
                    System.out.println("The " + clazzAssociate.getName() +
                            " with ID: " + associateID + " doesn't exist.");
                    return;
                }

                if (base.containsKey(baseID)) {
                    baseObj = base.get(baseID);
                } else {
                    System.out.println("The " + clazzBase.getName() +
                            " with ID: " + baseID + " doesn't exist.");
                    return;
                }

                /* there are 2 types of associations:
                book with author
                something with book
                retailer with something
                 */

                if (clazzAssociate.getName().equals("Author"))
                    ((Book) baseObj).addAuthor((Author) assocObj);

                else if (clazzBase.getName().equals("PublishingRetailer")) {
                    PublishingRetailer retail = (PublishingRetailer) baseObj;
                    switch (clazzAssociate.getName()) {
                        case "Country" -> retail.addCountry((Country) assocObj);
                        case "Book", "EditorialGroup", "PublishingBrand" -> retail.addPublishingArtifact
                                ((IPublishingArtifact) assocObj);
                        default -> throw new IllegalArgumentException
                                ("Illegal class");
                    }
                } else if (clazzAssociate.getName().equals("Book")) {
                    Book book = (Book) assocObj;
                    switch (clazzBase.getName()) {
                        case "EditorialGroup" -> ((EditorialGroup) baseObj).
                                addBook(book);
                        case "PublishingBrand" -> ((PublishingBrand) baseObj).
                                addBook(book);
                        default -> throw new IllegalStateException
                                ("Illegal class");
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(path + " file error!");
            ex.printStackTrace();
        }
    }

    public static void associateAll(HashMap<Integer, Book> books,
                                    HashMap<Integer, Author> authors,
                                    HashMap<Integer, EditorialGroup> groups,
                                    HashMap<Integer, PublishingBrand> brands,
                                    HashMap<Integer, Country> countries,
                                    HashMap<Integer, PublishingRetailer>
                                            retailers) {
        /* for Windows path should have '/' at the beginning
           for Linux there should be no '/' at the beginning */
        Associator.baseWithAssoc(books, authors, "/init/books-authors.in",
                Book.class, Author.class);

        Associator.baseWithAssoc(groups, books,
                "/init/editorial-groups-books.in", EditorialGroup.class,
                Book.class);

        Associator.baseWithAssoc(brands, books,
                "/init/publishing-brands-books.in", PublishingBrand.class,
                Book.class);

        Associator.baseWithAssoc(retailers, countries,
                "/init/publishing-retailers-countries.in",
                PublishingRetailer.class, Country.class);

        Associator.baseWithAssoc(retailers, books,
                "/init/publishing-retailers-books.in",
                PublishingRetailer.class, Book.class);

        Associator.baseWithAssoc(retailers, groups,
                "/init/publishing-retailers-editorial-groups.in",
                PublishingRetailer.class, EditorialGroup.class);

        Associator.baseWithAssoc(retailers, brands,
                "/init/publishing-retailers-publishing-brands.in",
                PublishingRetailer.class, PublishingBrand.class);
    }
}
