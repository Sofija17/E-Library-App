package mk.ukim.finki.wp.labgra.dto;

import mk.ukim.finki.wp.labgra.model.domain.Author;
import mk.ukim.finki.wp.labgra.model.domain.Book;
import mk.ukim.finki.wp.labgra.model.enums.Categories;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(
        Long id,
        String name,
        Categories category,
        Long authorId,
        Integer availableCopies
) {

    public static DisplayBookDto from (Book book){
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<DisplayBookDto> from (List<Book> books){
        return books.stream().map(book -> DisplayBookDto.from(book)).collect(Collectors.toList());
    }

    public Book toBook (Author author){
        return new Book(name, category,author,availableCopies);
    }
}
