package mk.ukim.finki.wp.labgra.service.domain;

import mk.ukim.finki.wp.labgra.model.domain.Book;
import mk.ukim.finki.wp.labgra.model.domain.BookHistory;
//import mk.ukim.finki.wp.labgra.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;


public interface BookService  {
    List<Book> findAll();
    Optional <Book> update(Long id, Book book);
    Optional <Book> save(Book book);
    void deleteById(Long id);
    Optional<Book> findById(Long id);
    boolean rentedBook(Long bookId);
    List<BookHistory> findHistoryByBook(Long bookId);


//    void refreshMaterializedView();
//    List<BooksPerAuthorView> allBooksPerAuthors();
}
