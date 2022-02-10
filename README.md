# Dumitru Radu-Andrei 322CB

## Interfete

* [IPublishingArtifact]: contine o metoda care intoarce un string

## Clase

* Toate clasele implementate au campurile cerute, constructor cu toti parametrii si metode getter si setter(am ales sa
  implementez si setter pentru un caz real, ca de exemplu, s-a gresit titlul unei carti si se doreste modificarea lui,
  iar warning-urile pt. unused method au fost suprimate). Clasele care contin si liste mai au si o metoda care permite
  adaugarea unui element in lista.

### Book

* [authorListString]: parcurge toti autorii din lista si construieste un string cu numele acestora.
* [getBookDetails]: construieste un string care respecta formatul xml si care contine informatii despre carte.
* [getAllBooksToXML]: primeste o lista de carti, iar pentru fiecare apeleaza getBookDetails. Rezultatul este un string
  format xml.
* [Publish]: override la publish, un xml complet cu informatiile cartii.

### Author

* [getFullName]: intoarce un string cu numele si prenumele unui autor.

### EditorialGroup

* [getEditorialGroupDetails]: construieste un string format xml cu detaliile unui editorial group.
* [Publish]: override la publish, un xml complet cu informatiile despre grup si la despre toate cartile editate de
  acesta.

### PublishingBrand

* analog cu metodele din EditorialGroup, dar pentru a gestiona informatiile unui Publishing Brand.

### Publishing Retailer, Language

* nu au alte metode, pe langa cele generale, prezentate mai sus.

### Country

* [getAllCountries]: primeste o lista de carti si intoarce un string cu numele acestora, folosita la testare.

### HashMappers

* metodele implementate aici, primesc ca parametru o cale si intorc un HashMap de tipul (Integer, TIP) in care se retin
  obiectele.

* [parseBook]: citeste dintr-un fisier cu urmatorul template:
  [ID###Name###Subtitle###ISBN###PageCount###Keywords###LanguageId###CreatedOn]. Intoarce un hashmap cu toate cartile
  citite.

* [parseAuthor]: citeste dintr-un fisier cu urmatorul template:
  [Id###FirstName###LastName]. Intoarce un hashmap cu toti autorii cititi.

* [parseType]: citeste dintr-un fisier cu urmatorul template:
  [Id###Name]. Hashmapul returnat poate retine informatii de tipul:
  EditorialGroup, PublishingBrand, PublishingRetailer, Country, pentru ca acestea au acelasi format. Se decide ce
  hashmap se va intoarce in functie de clasa data ca parametru.

* [parseLang]: citeste dintr-un fisier cu urmatorul template:
  [Id###Code###Translation]. Se intoarce un hashmap cu toate limbile introduse.

### Associator

* [baseWithAssoc]: toate fisierele care ofera informatii despre asocieri au acelasi format: [base###assoc]. Astfel,
  pentru a nu crea mai multe metode, am folosit genericitate. Exista 3 tipuri posibile de asocieri: carte cu autor, TIP
  cu carte si Retailer cu TIP. Se identifica tipul de asociere, iar apoi se introduce, in toate cazurile, intr-o lista
  obiectul dorit pentru a realiza asocierea.

* [associateAll]: apeleaza asocierea pentru toate fisierele date. L-am scris aici pentru a nu polua main-ul.

### Administration

* se definesc toate hashmap-urile necesare.
* [getRetailerMap]: pentru un retailer, se cauta toate cartile publicate de acesta(fie direct, fie prin EditorialGroup,
  fie prin PublishingBrand), se genereaza un hashmap cu acestea.

* [getBooksForPublishingRetailerID]: creez map-ul pentru retailer, il pun in lista pentru a returna ce este specificat
  in cerinta.

* [getLanguagesForPublishingRetailerID]: creez map-ul, pentru fiecare carte, iau id-ul limbii, o iau din hashmap, daca
  nu am introdus-o deja in lista, o introduc.

* [getCountriesForBookID]: analog cu getLanguages.

* [getCommonBooksForRetailerIDs]: fac map-uri pentru ambii retaileri. Iterez prin primul, iar daca al doilea contine si
  el cheia, atunci pun in lista. Complexitate: O(2n + m) = O(max(m,n)), dupa calculele mele.
*
* [getAllBooksForRetailerIDs]: fac map-uri pentru ambii retaileri, iau fiecare element din primul, il pun in lista, iar
  daca si al doilea il contine, atunci il sterg(din al doilea map). Iterez apoi prin elementele ramase din cel de al
  doilea map, adaugand tot in lista. Complexitate: O(2n+2m) = O(max(m,n)) (sau cel putin asa sper).

* [main]:  generez hashmapurile, fac asocierile, apelez testele.

### Tester

* contine metode care au un vector cu id-uri. Apelez fiecare metoda implementata in Administration. Rezultatele testelor
  rulate de mine au fost puse in folderul myResults.


