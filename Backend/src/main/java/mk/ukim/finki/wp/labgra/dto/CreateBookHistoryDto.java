package mk.ukim.finki.wp.labgra.dto;

import mk.ukim.finki.wp.labgra.model.domain.Author;
import mk.ukim.finki.wp.labgra.model.domain.Book;
import mk.ukim.finki.wp.labgra.model.domain.BookHistory;
import mk.ukim.finki.wp.labgra.model.enums.Categories;

import java.time.LocalDateTime;

public record CreateBookHistoryDto(
        String name,
        Categories category,
        Long authorId,
        Integer availableCopies,
        LocalDateTime dateModified
) {

    //od Book da pretvoris vo CreateBookDto
    public static CreateBookHistoryDto from(BookHistory bookHistory) {
        return new CreateBookHistoryDto(
                bookHistory.getName(),
                bookHistory.getCategory(),
                bookHistory.getAuthor().getId(),
                bookHistory.getAvailableCopies(),
                LocalDateTime.now()
        );
    }

    public BookHistory toBookHistory(Author author) {
        return new BookHistory(name, category, author, availableCopies, dateModified);
    }
}
