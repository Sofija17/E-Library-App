package mk.ukim.finki.wp.labgra.dto;

import mk.ukim.finki.wp.labgra.model.domain.Author;
import mk.ukim.finki.wp.labgra.model.domain.Book;
import mk.ukim.finki.wp.labgra.model.domain.BookHistory;
import mk.ukim.finki.wp.labgra.model.enums.Categories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookHistoryDto(
        Long id,
        String name,
        Categories category,
        Long authorId,
        Integer availableCopies,
        LocalDateTime dateModified
) {

    public static DisplayBookHistoryDto from (BookHistory bookHistory){
        return new DisplayBookHistoryDto(
                bookHistory.getId(),
                bookHistory.getName(),
                bookHistory.getCategory(),
                bookHistory.getAuthor().getId(),
                bookHistory.getAvailableCopies(),
                LocalDateTime.now()
        );
    }

    public static List<DisplayBookHistoryDto> from (List<BookHistory> books){
        return books.stream().map(bookHistory -> DisplayBookHistoryDto.from(bookHistory)).collect(Collectors.toList());
    }

    public BookHistory toBookHistory (Author author){
        return new BookHistory(name, category,author,availableCopies, dateModified);
    }

}
