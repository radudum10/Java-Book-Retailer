import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class HashMappers {
    public static HashMap<Integer, Book> parseBook(String path) {
        File file = new File(path);
        HashMap<Integer, Book> books = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skipping the template
            String line;
            while ((line = br.readLine()) != null) {
                int k = 0; // index for the token
                String[] tokens = line.split("###");

                int id = Integer.parseInt(tokens[k++]);
                String name = tokens[k++];

                if (books.containsKey(id)) {
                    System.out.println("ID " + id + " already exists. Book "
                            + name + " won't be added. Please provide a " +
                            "unique ID for each book.");
                    continue;
                }

                String subtitle = tokens[k++];
                String isbn = tokens[k++];
                int pagecount = Integer.parseInt(tokens[k++]);
                String keywords = tokens[k++];
                int languageid = Integer.parseInt(tokens[k++]);
                Date date = new SimpleDateFormat("dd.MM.yy HH:mm:ss").
                        parse(tokens[k]);

                Book book = new Book(id, name, subtitle, isbn, pagecount,
                        keywords, languageid, date);

                books.put(id, book); // id is used as key
            }
        } catch (IOException | ParseException ex) {
            System.out.println(path + " file error!");
            ex.printStackTrace();
        }

        return books;
    }

    public static HashMap<Integer, Author> parseAuthor(String path) {
        File file = new File(path);
        HashMap<Integer, Author> authors = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skipping the template
            String line;
            while ((line = br.readLine()) != null) {
                int k = 0; // index for the token
                String[] tokens = line.split("###");

                int id = Integer.parseInt(tokens[k++]);
                String firstName = tokens[k++];
                String lastName = tokens[k];

                if (authors.containsKey(id)) {
                    System.out.println("ID " + id + " already exists. Author "
                            + firstName + " " + lastName + " won't be added."
                            + " Please provide a "
                            + "unique ID for each author.");

                    continue;
                }
                Author author = new Author(id, firstName, lastName);

                authors.put(id, author); // id is used as key
            }
        } catch (IOException ex) {
            System.out.println(path + " file error!");
            ex.printStackTrace();
        }

        return authors;
    }

    public static <T> HashMap<Integer, T> parseType(String path,
                                                    Class<T> clazz) {

        /* works only for:
        -> EditorialGroup
        -> PublishingBrand
        -> PublishingRetailer
        -> Country
        because they have the same file template
         */

        File file = new File(path);
        HashMap<Integer, T> target = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skipping the template
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                int id = Integer.parseInt(tokens[0]);

                if (target.containsKey(id)) {

                    System.out.println("ID " + id + " already exists. " +
                            clazz.getName() + " " + tokens[1] +
                            " won't be added." + " Please provide a " +
                            "unique ID for each " + clazz.getName() + ".");

                    continue;
                }

                Object obj = switch (clazz.getName()) { // all classes extend Object
                    case "EditorialGroup" -> new EditorialGroup(id,
                            tokens[1]);
                    case "PublishingBrand" -> new PublishingBrand(id,
                            tokens[1]);
                    case "PublishingRetailer" -> new PublishingRetailer(id,
                            tokens[1]);
                    case "Country" -> new Country(id, tokens[1]);
                    default -> throw new IllegalArgumentException
                            ("Invalid Class");
                };

                // using Class.Cast() to avoid unchecked Cast to type T
                target.put(id, clazz.cast(obj));

            }
        } catch (IOException ex) {
            System.out.println(path + " file error!");
            ex.printStackTrace();
        }

        return target;
    }

    public static HashMap<Integer, Language> parseLang(String path) {
        File file = new File(path);
        HashMap<Integer, Language> languages = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skipping the template
            String line;
            while ((line = br.readLine()) != null) {
                int k = 0; // index for the token
                String[] tokens = line.split("###");
                int id = Integer.parseInt(tokens[k++]);
                String code = tokens[k++];
                String translation = tokens[k];
                Language lang = new Language(id, code, translation);
                languages.put(id, lang);
            }
        } catch (IOException ex) {
            System.out.println("Language file error!");
            ex.printStackTrace();
        }

        return languages;
    }
}
