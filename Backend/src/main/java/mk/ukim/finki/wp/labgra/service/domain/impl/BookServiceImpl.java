package mk.ukim.finki.wp.labgra.service.domain.impl;

import mk.ukim.finki.wp.labgra.events.BookDataChangedEvent;
import mk.ukim.finki.wp.labgra.model.domain.Book;
import mk.ukim.finki.wp.labgra.model.domain.BookHistory;
//import mk.ukim.finki.wp.labgra.model.views.BooksPerAuthorView;
import mk.ukim.finki.wp.labgra.repository.BookHistoryRepository;
import mk.ukim.finki.wp.labgra.repository.BookRepository;
//import mk.ukim.finki.wp.labgra.repository.BooksPerAuthorViewRepository;
import mk.ukim.finki.wp.labgra.service.domain.AuthorService;
import mk.ukim.finki.wp.labgra.service.domain.BookService;
import mk.ukim.finki.wp.labgra.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CountryService countryService;
    private final BookHistoryRepository bookHistoryRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
   // private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CountryService countryService, BookHistoryRepository bookHistoryRepository, ApplicationEventPublisher applicationEventPublisher
                           //,BooksPerAuthorViewRepository booksPerAuthorViewRepository
    ) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.countryService = countryService;
        this.bookHistoryRepository = bookHistoryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
      //  this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    public List<BookHistory> findHistoryByBook(Long bookId){

            return bookHistoryRepository.findAllByBookId(bookId);
    }


    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id).map(existingBook -> {

            BookHistory bookHistory = new BookHistory();
            bookHistory.setBook(existingBook);
            bookHistory.setName(existingBook.getName());
            bookHistory.setCategory(existingBook.getCategory());
            bookHistory.setAuthor(existingBook.getAuthor());
            bookHistory.setAvailableCopies(existingBook.getAvailableCopies());
            bookHistory.setDateModified(LocalDateTime.now());

            bookHistoryRepository.save(bookHistory); // !!!!!!!!!!!!!!

            if (book.getAuthor().getId() != null && authorService.findById(book.getAuthor().getId()).isPresent()) {
                existingBook.setAuthor(authorService.findById(book.getAuthor().getId()).get());
            }
            if (book.getName() != null) {
                existingBook.setName(book.getName());
            }
            if (book.getAvailableCopies() != null) {
                existingBook.setAvailableCopies(book.getAvailableCopies());
            }
            if (book.getCategory() != null) {
                existingBook.setCategory(book.getCategory());
            }
            //bookHistoryRepository.save(bookHistory);
//            this.applicationEventPublisher.publishEvent(new BookDataChangedEvent(book));
           // this.refreshMaterializedView();
            return bookRepository.save(existingBook);
        });
    }

    @Override
    public Optional<Book> save(Book book) {
        if (book.getAuthor().getId() != null && authorService.findById(book.getAuthor().getId()).isPresent()
                && book.getCategory() != null) {
            Book saved = bookRepository.save(
                    new Book(
                            book.getName(),
                            book.getCategory(),
                            authorService.findById(book.getAuthor().getId()).get(),
                            book.getAvailableCopies()));

           // this.refreshMaterializedView();
//            this.applicationEventPublisher.publishEvent(new BookDataChangedEvent(saved));
            return Optional.of(saved);
        }
        return Optional.empty();
    }


    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
       // this.refreshMaterializedView();
        //this.applicationEventPublisher.publishEvent(new BookDataChangedEvent(null));
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean rentedBook(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();

            if (book.getAvailableCopies() > 0) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                bookRepository.save(book);
                return true;
            }
        }
        return false;
    }
//    @Override
//    public void refreshMaterializedView() {
//        booksPerAuthorViewRepository.refreshMaterializedView();
//    }

//    @Override
//    public List<BooksPerAuthorView> allBooksPerAuthors() {
//        return booksPerAuthorViewRepository.findAll();
//    }
}
