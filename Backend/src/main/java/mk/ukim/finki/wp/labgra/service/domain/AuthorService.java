package mk.ukim.finki.wp.labgra.service.domain;

import mk.ukim.finki.wp.labgra.model.domain.Author;
import mk.ukim.finki.wp.labgra.model.projections.AuthorProjection;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> save(Author author);

    Optional<Author> findById(Long id);

    Optional<Author> update(Long id, Author author);

    void deleteById(Long id);

    List<AuthorProjection> findAllAuthorNames();
}
