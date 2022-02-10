import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Tester {
    public void createFile(String filename) {
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile())
                System.out.println("Test file " + filename + " created.");
            else
                System.out.println("Test file " + filename +
                        " already exists.");

        } catch (IOException e) {
            System.out.println("Creating file ERROR.");
            e.printStackTrace();
        }
    }

    public void booksForPublishingRetailerID() {
        String filename = "booksForPublishingRetailerID-TEST.txt";
        createFile(filename);

        int[] arr = {1, 2, 3, 4, 5};

        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("Results for getBooksForPublishingRetailerID\n");
            for (int id : arr) {
                myWriter.write("For the retailer with ID: " + id + '\n');
                List<Book> bookList = Administration.
                        getBooksForPublishingRetailerID(id);

                for (Book book : bookList)
                    myWriter.write(book.Publish());

            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR at writing.");
            e.printStackTrace();
        }
    }

    public void languagesForPublishingRetailerID() {
        String filename = "languagesForPublishingRetailerID-TEST.txt";
        createFile(filename);

        int[] arr = {1, 2, 11, 13, 14};

        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write
                    ("Results for getLanguagessForPublishingRetailerID\n");
            for (int id : arr) {
                myWriter.write("For the retailer with ID: " + id + '\n');
                List<Language> langList = Administration.
                        getLanguagesForPublishingRetailerID(id);

                for (Language lang : langList)
                    myWriter.write(lang.getName() + " ");

                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR at writing.");
            e.printStackTrace();
        }
    }

    public void countriesForBookID() {
        String filename = "countriesForBookID-TEST.txt";
        createFile(filename);

        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("Results for countriesForBookID\n");

            int[] arr = {204, 224, 262, 275, 291};

            for (int id : arr) {
                myWriter.write("For the Retailer with ID: " + id + '\n');
                List<Country> countryList = Administration.
                        getCountriesForBookID(id);
                myWriter.write(Country.getAllCountries(countryList));
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR at writing.");
            e.printStackTrace();
        }
    }

    public void commonBooksForRetailerIDs() {
        String filename = "commonBooks-TEST.txt";
        createFile(filename);

        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("Results for commonBooks\n");

            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            for (int i = 0; i < 5; i++) {
                myWriter.write("For the Retailers with IDs: " + arr[i] +
                        " and " + arr[i + 5] + '\n');

                List<Book> bookList = Administration.
                        getCommonBooksForRetailerIDs(arr[i], arr[i + 5]);

                for (Book book : bookList)
                    myWriter.write(book.Publish());
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR at writing.");
            e.printStackTrace();
        }
    }

    public void allBooksForRetailerIDs() {
        String filename = "allBooks-TEST.txt";
        createFile(filename);

        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("Results for allBooks\n");

            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            for (int i = 0; i < 5; i++) {
                myWriter.write("For the Retailers with IDs: " + arr[i] +
                        " and " + arr[i + 5] + '\n');

                List<Book> bookList = Administration.
                        getAllBooksForRetailerIDs(arr[i], arr[i + 5]);

                for (Book book : bookList)
                    myWriter.write(book.Publish());
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR at writing.");
            e.printStackTrace();
        }
    }
}
