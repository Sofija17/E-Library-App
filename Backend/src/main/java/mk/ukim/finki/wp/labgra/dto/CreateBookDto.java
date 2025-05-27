package mk.ukim.finki.wp.labgra.dto;

import mk.ukim.finki.wp.labgra.model.domain.Author;
import mk.ukim.finki.wp.labgra.model.domain.Book;
import mk.ukim.finki.wp.labgra.model.enums.Categories;

public record CreateBookDto(
        String name,
        Categories category,
        Long authorId,
        Integer availableCopies
        ){

//od Book da pretvoris vo CreateBookDto
    public static CreateBookDto from (Book book){
        return new CreateBookDto(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public Book toBook (Author author){
        return new Book(name, category,author,availableCopies);
    }

}