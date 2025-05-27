package mk.ukim.finki.wp.labgra.service.application.impl;

import mk.ukim.finki.wp.labgra.dto.CreateBookDto;
import mk.ukim.finki.wp.labgra.dto.DisplayBookDto;
import mk.ukim.finki.wp.labgra.dto.DisplayBookHistoryDto;
import mk.ukim.finki.wp.labgra.model.domain.Author;
import mk.ukim.finki.wp.labgra.model.enums.Categories;
import mk.ukim.finki.wp.labgra.service.application.BookApplicationService;
import mk.ukim.finki.wp.labgra.service.domain.AuthorService;
import mk.ukim.finki.wp.labgra.service.domain.BookService;
import mk.ukim.finki.wp.labgra.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    public final BookService bookService;
    public final AuthorService authorService;
    public final CountryService countryService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto bookDto) {
        Optional<Author> author = authorService.findById(bookDto.authorId());
        Categories category = bookDto.category();

        return bookService.update(id, bookDto.toBook(author.orElse(null)))
                .map(book -> DisplayBookDto.from(book));

    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto bookDto) {
        Optional<Author> author = authorService.findById(bookDto.authorId());
        Categories category = bookDto.category();

        if(author.isPresent() && category!=null){
            return bookService.save(bookDto.toBook(author.orElse(null)))
                    .map(book -> DisplayBookDto.from(book));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(book -> DisplayBookDto.from(book));
    }

    @Override
    public boolean rentedBook(Long bookId) {
        return bookService.rentedBook(bookId);
    }

    @Override
    public List<DisplayBookHistoryDto> findHistoryByBook(Long bookId) {
        return bookService.findHistoryByBook(bookId).stream().map(DisplayBookHistoryDto::from).collect(Collectors.toList());
    }
}
