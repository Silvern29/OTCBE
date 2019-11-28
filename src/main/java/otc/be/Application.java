package otc.be;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
//    @Autowired
//    private BookRepository bookRepository;
//    @Autowired
//    private AuthorRepository authorRepository;
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Autowired
//    private CreditCardRepository creditCardRepository;
//    @Autowired
//    private HistoryRepository historyRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
//            authorRepository.deleteAll();
//            bookRepository.deleteAll();
//            creditCardRepository.deleteAll();
//            customerRepository.deleteAll();
//            historyRepository.deleteAll();
//
//            Author a1 = new Author("Simon", "Beckett", new Date(1960-01-01));
//            authorRepository.save(a1);
//            Author a2 = new Author("Cody", "McFadyen", new Date(1968-01-01));
//            authorRepository.save(a2);
//            Author a3 = new Author("William", "Shakespear", new Date(1564-01-01));
//            authorRepository.save(a3);
//            Author a4 = new Author("Steven", "King", new Date(1947-01-01));
//            authorRepository.save(a4);
//            Author a5 = new Author("Thomas", "Harris", new Date(1940-01-01));
//            authorRepository.save(a5);
//            Author a6 = new Author("Günther", "Grass", new Date(1927-01-01));
//            authorRepository.save(a6);
//            Author a7 = new Author("Sebastian", "Fitzek", new Date(1971-01-01));
//            authorRepository.save(a7);
//            Author a8 = new Author("JRR", "Tolkien", new Date(1892-01-01));
//            authorRepository.save(a8);
//            Author a9 = new Author("Joanne K.", "Rowling", new Date(1965-01-01));
//            authorRepository.save(a9);
//
//            Book b1 = new Book("Hannibal", a5, "Thriller", "ISBN 3-453-43170-7", 18);
//            bookRepository.save(b1);
//            Book b2 = new Book("Das Schweigen der Lämmer", a5, "Thriller", "ISBN 3-453-03781-2", 18);
//            bookRepository.save(b2);
//            Book b3 = new Book("Die Therapie", a7, "Thriller", "ISBN 3-426-63309-4", 18);
//            bookRepository.save(b3);
//            Book b4 = new Book("Der Augensammler", a7, "Thriller", "ISBN 3-426-19851-7.", 18);
//            bookRepository.save(b4);
//            Book b5 = new Book("Das Paket", a7, "Thriller", "ISBN 978-3-426-19920-6", 18);
//            bookRepository.save(b5);
//            Book b6 = new Book("Harry Potter und der Stein der Weisen", a9, "Fantasy", "ISBN 978-3-551-55167-2", 6);
//            bookRepository.save(b6);
//            Book b7 = new Book("Harry Potter und die Kammer des Schreckens", a9, "Fantasy", "ISBN 3-453-03781-2", 6);
//            bookRepository.save(b7);
//            Book b8 = new Book("Harry Potter und der Gefangene von Askaban", a9, "Fantasy", "ISBN 3-453-03781-2", 6);
//            bookRepository.save(b8);
//            Book b9 = new Book("Harry Potter und der Feuerkelch", a9, "Fantasy", "ISBN 3-453-03781-2", 6);
//            bookRepository.save(b9);
//            Book b10 = new Book("Harry Potter and the Order of the Phoenix", a9, "Fantasy", "ISBN 978-0-7475-5100-3", 6);
//            bookRepository.save(b10);
//            Book b11 = new Book("The Hobbit", a8, "Fantasy", "ISBN 0-395-47690-9", 12);
//            bookRepository.save(b11);
//            Book b12 = new Book("Der Herr der Ringe - Die Gefährten", a8, "Fantasy", "ISBN 978-3-608-93541-7", 12);
//            bookRepository.save(b12);
//            Book b13 = new Book("Der Herr der Ringe - Die zwei Türme", a8, "Fantasy", "ISBN 978-3-608-93542-4", 12);
//            bookRepository.save(b13);
//            Book b14 = new Book("Der Herr der Ringe - Die Wiederkehr des Königs", a8, "Fantasy", "ISBN 978-3-608-93543-1", 12);
//            bookRepository.save(b14);
//            Book b15 = new Book("Die Blechtrommel", a6, "Roman", "ISBN 978-3-423-13819-2", 12);
//            bookRepository.save(b15);
//            Book b16 = new Book("Brennen muss Salem", a4, "Thriller", "ISBN 3-453-02053-7", 18);
//            bookRepository.save(b16);
//            Book b17 = new Book("Shakespeare’s dramatische Werke", a3, "Sammelwerk", "ISBN 3-453-03781-2", 12);
//            bookRepository.save(b17);
//            Book b18 = new Book("Die Chemie des Todes", a1, "Thriller", "ISBN 978-3-8052-0811-6", 18);
//            bookRepository.save(b18);
//            Book b19 = new Book("Ausgelöscht", a2, "Thriller", "ISBN 978-3-7857-2390-6", 18);
//            bookRepository.save(b19);
//
//            CreditCard cc1 = new CreditCard("1258456945687254", 159, "02/20");
//            creditCardRepository.save(cc1);
//            CreditCard cc2 = new CreditCard("5645631564452139", 852, "12/19");
//            creditCardRepository.save(cc2);
//            CreditCard cc3 = new CreditCard("1456445454475821", 456, "04/23");
//            creditCardRepository.save(cc3);
//            CreditCard cc4 = new CreditCard("8979745456745862", 147, "10/21");
//            creditCardRepository.save(cc4);
//            CreditCard cc5 = new CreditCard("8979845465745826", 369, "08/20");
//            creditCardRepository.save(cc5);
//
//            Customer c1 = new Customer(1234, "test@derver.at", "Horst", "Lichter", new Date(1925 - 10 - 1), "AtHome", cc1);
//            customerRepository.save(c1);
//            Customer c2 = new Customer(1234, "test@derver.at", "Heinz", "Müller", new Date(1963 - 12 - 10), "AtHome", cc2);
//            customerRepository.save(c2);
//            Customer c3 = new Customer(1234, "test@derver.at", "Thomas", "Meier", new Date(1901 - 8 - 5), "AtHome", cc3);
//            customerRepository.save(c3);
//            Customer c4 = new Customer(1234, "test@derver.at", "Karl", "Muster", new Date(1950 - 1 - 1), "AtHome", cc4);
//            customerRepository.save(c4);
//            Customer c5 = new Customer(1234, "test@derver.at", "Dieter", "Heinz", new Date(1965 - 5 - 15), "AtHome", cc5);
//            customerRepository.save(c5);
//
//            History h1 = new History(c1, b9, new Date(1524654646));
//            historyRepository.save(h1);
//            History h2 = new History(c2, b5, new Date(1524654646));
//            historyRepository.save(h2);
//            History h3 = new History(c3, b10, new Date(1524654646));
//            historyRepository.save(h3);
//            History h4 = new History(c4, b2, new Date(1524654646));
//            historyRepository.save(h4);
//            History h5 = new History(c5, b7, new Date(1524654646));
//            historyRepository.save(h5);
//            History h6 = new History(c1, b8, new Date(1524654646), new Date(1524654646));
//            historyRepository.save(h6);
//            History h7 = new History(c2, b15, new Date(1524654646));
//            historyRepository.save(h7);
//            History h8 = new History(c3, b18, new Date(1524654646));
//            historyRepository.save(h8);
//            History h9 = new History(c3, b14, new Date(1524654646), new Date(1524654646));
//            historyRepository.save(h9);
//            History h10 = new History(c3, b1, new Date(1524654646));
//            historyRepository.save(h10);
        };
    }
}
