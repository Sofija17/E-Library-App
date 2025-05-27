package mk.ukim.finki.wp.labgra.service.application;

import mk.ukim.finki.wp.labgra.dto.CreateBookDto;
import mk.ukim.finki.wp.labgra.dto.DisplayBookDto;
import mk.ukim.finki.wp.labgra.dto.DisplayBookHistoryDto;
import mk.ukim.finki.wp.labgra.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<DisplayBookDto> findAll();
    Optional<DisplayBookDto> update(Long id, CreateBookDto bookDto);
    Optional <DisplayBookDto> save(CreateBookDto bookDto);
    void deleteById(Long id);
    Optional<DisplayBookDto> findById(Long id);
    boolean rentedBook(Long bookId);
    List<DisplayBookHistoryDto> findHistoryByBook(Long bookId);
}
