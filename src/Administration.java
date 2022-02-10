import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Administration {
    static HashMap<Integer, Book> books;
    static HashMap<Integer, Language> languages;
    static HashMap<Integer, Author> authors;
    static HashMap<Integer, EditorialGroup> groups;
    static HashMap<Integer, PublishingBrand> brands;
    static HashMap<Integer, PublishingRetailer> retailers;
    static HashMap<Integer, Country> countries;

    public static HashMap<Integer, Book> getRetailerMap(int retailerID) {
        if (!retailers.containsKey(retailerID))
            throw new IllegalArgumentException
                    ("The Publishing Retailer with ID: " +
                            retailerID + " doesn't exist!");

        PublishingRetailer retailer = retailers.get(retailerID);
        HashMap<Integer, Book> retailerMap = new HashMap<>();

        /* getting all books, all editorial group books, all publishing brands books */
        for (IPublishingArtifact artif : retailer.getPublishingArtifacts()) {
            if (artif instanceof Book) {
                int id = ((Book) artif).getId();
                if (retailerMap.containsKey(id))
                    continue;

                retailerMap.put(id, (Book) artif);
            } else if (artif instanceof EditorialGroup) {
                for (Book book : ((EditorialGroup) artif).getBooks()) {
                    int id = book.getId();
                    if (retailerMap.containsKey(id))
                        continue;

                    retailerMap.put(id, book);
                }
            } else if (artif instanceof PublishingBrand) {
                for (Book book : ((PublishingBrand) artif).getBooks()) {
                    int id = book.getId();
                    if (retailerMap.containsKey(id))
                        continue;

                    retailerMap.put(id, book);
                }
            }
        }

        return retailerMap;
    }

    public static List<Book> getBooksForPublishingRetailerID
            (int publishingRetailerID) {
        HashMap<Integer, Book> retailerMap = getRetailerMap
                (publishingRetailerID);

        return new ArrayList<>(retailerMap.values());
    }

    public static List<Language> getLanguagesForPublishingRetailerID
            (int publishingRetailerID) {
        List<Language> languageList = new ArrayList<>();
        HashMap<Integer, Book> retailerMap = getRetailerMap
                (publishingRetailerID);

        /* for every book, store the language without duplicates */
        for (Map.Entry<Integer, Book> entry : retailerMap.entrySet()) {
            Book book = entry.getValue();
            int languageID = book.getLanguageID();
            Language lang = languages.get(languageID);

            if (languageList.contains(lang))
                continue;

            languageList.add(lang);
        }

        return languageList;
    }

    public static List<Country> getCountriesForBookID(int bookID) {
        List<Country> countryList = new ArrayList<>();

        /* finding the book */
        if (!books.containsKey(bookID))
            throw new IllegalArgumentException("Invalid bookID: " + bookID);

        Book targetBook = books.get(bookID);

        /* looking for all retailers who published the book
        and storing the country where they had published it
         */
        for (int retailerID : retailers.keySet()) {
            List<Book> bookList = getBooksForPublishingRetailerID
                    (retailerID);

            if (bookList.contains(targetBook))
                for (Country country : (retailers.get(retailerID)).
                        getCountries())
                    if (!countryList.contains(country))
                        countryList.add(country);
        }
        return countryList;
    }

    public static List<Book> getCommonBooksForRetailerIDs(int retailerID1,
                                                          int retailerID2) {

        List<Book> bookList = new ArrayList<>();

        HashMap<Integer, Book> retailerMap1 = getRetailerMap(retailerID1);
        HashMap<Integer, Book> retailerMap2 = getRetailerMap(retailerID2);

        for (int targetID : retailerMap1.keySet()) {
            if (retailerMap1.containsKey(targetID) &&
                    retailerMap2.containsKey(targetID))
                bookList.add(retailerMap1.get(targetID));
        }

        return bookList;
    }

    public static List<Book> getAllBooksForRetailerIDs(int retailerID1,
                                                       int retailerID2) {
        List<Book> bookList = new ArrayList<>();

        HashMap<Integer, Book> retailerMap1 = getRetailerMap(retailerID1); // O(n)
        HashMap<Integer, Book> retailerMap2 = getRetailerMap(retailerID2); // O(m)

        for (HashMap.Entry<Integer, Book> entry : retailerMap1.entrySet()) { // O(n)
            Book target = entry.getValue();
            int id = target.getId();

            retailerMap2.remove(id); // in order to remove duplicates

            bookList.add(target);
        }

        for (HashMap.Entry<Integer, Book> entry : retailerMap2.entrySet()) {
            Book target = entry.getValue();
            bookList.add(target);
        }

        /* => method complexity: O(2n + 2m) = O(n) */
        return bookList;
    }

    public static void main(String[] args) {
        /* for Windows path should have '/' at the beginning
           for Linux there should be no '/' at the beginning */
        books = HashMappers.parseBook("/init/books.in");

        languages = HashMappers.parseLang("/init/languages.in");

        authors = HashMappers.parseAuthor("/init/authors.in");

        groups = HashMappers.parseType("/init/editorial-groups.in",
                EditorialGroup.class);

        brands = HashMappers.parseType("/init/publishing-brands.in",
                PublishingBrand.class);

        retailers = HashMappers.parseType("/init/publishing-retailers.in",
                PublishingRetailer.class);

        countries = HashMappers.parseType("/init/countries.in", Country.class);

        Associator.associateAll(books, authors, groups, brands, countries,
                retailers);

        Tester tester = new Tester();
        tester.booksForPublishingRetailerID();
        tester.languagesForPublishingRetailerID();
        tester.countriesForBookID();
        tester.commonBooksForRetailerIDs();
        tester.allBooksForRetailerIDs();
    }
}
