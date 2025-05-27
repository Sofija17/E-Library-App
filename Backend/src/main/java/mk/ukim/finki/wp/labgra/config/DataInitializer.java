package mk.ukim.finki.wp.labgra.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.labgra.model.domain.*;
import mk.ukim.finki.wp.labgra.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import mk.ukim.finki.wp.labgra.model.enums.Categories;
import org.springframework.stereotype.Component;
import mk.ukim.finki.wp.labgra.model.enums.Role;

import java.util.List;

@Component
public class DataInitializer {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WishlistRepository wishlistRepository;

    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, WishlistRepository wishlistRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.wishlistRepository = wishlistRepository;
    }

    @PostConstruct
    public void init() {
        Country country1 = new Country("Macedonia", "Europe");
        Country country2 = new Country("Canada", "North America");
        Country country3 = new Country("Japan", "Asia");
        Country country4 = new Country("The Netherlands", "Europe");
        Country country5 = new Country("Italy", "Europe");
        Country country6 = new Country("Singapore", "Asia");
        Country country7 = new Country("Finland", "Europe");

        Author author1 = new Author("Petko", "Petkovski", country1);
        Author author2 = new Author("John", "Doe", country1);
        Author author3 = new Author("Bella", "Orr", country2);
        Author author4 = new Author("Alaric", "Sherman", country2);
        Author author5 = new Author("Cataleya", "Odom", country1);
        Author author6 = new Author("Ruben", "Villalobos", country2);
        Author author7 = new Author("Beau", "Blanchard", country2);

        Book book1 = new Book("Book1", Categories.DRAMA, author1, 5);
        Book book2 = new Book("Book2", Categories.HISTORY, author2, 1);
        Book book3 = new Book("Book3", Categories.BIOGRAPHY, author1, 13);
        Book book4 = new Book("Book4", Categories.NOVEL, author1, 23);
        Book book5 = new Book("Book5", Categories.FANTASY, author1, 53);
        Book book6 = new Book("Book6", Categories.CLASSICS, author1, 23);
        Book book7 = new Book("Book7", Categories.THRILER, author1, 17);


        List<Book> list1 = List.of(book1);

        countryRepository.save(country1);
        countryRepository.save(country2);
        countryRepository.save(country3);
        countryRepository.save(country4);
        countryRepository.save(country5);
        countryRepository.save(country6);
        countryRepository.save(country7);


        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);
        authorRepository.save(author5);
        authorRepository.save(author6);
        authorRepository.save(author7);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
        bookRepository.save(book7);

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));

        User sofija = new User(
                "Sofija",
                passwordEncoder.encode("sofija"),
                "Sofija",
                "Kitanovikj",
                Role.ROLE_LIBRARIAN
        );
        userRepository.save(sofija);

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(sofija);
        wishlist.setBooks(List.of(book1, book2));
        userRepository.save(sofija);

        wishlistRepository.save(wishlist);
    }

}