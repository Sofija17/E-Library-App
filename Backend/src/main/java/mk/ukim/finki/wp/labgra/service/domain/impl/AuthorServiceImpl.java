package mk.ukim.finki.wp.labgra.service.domain.impl;

import mk.ukim.finki.wp.labgra.model.domain.Author;
import mk.ukim.finki.wp.labgra.model.projections.AuthorProjection;
import mk.ukim.finki.wp.labgra.repository.AuthorRepository;
import mk.ukim.finki.wp.labgra.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(Author author) {
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        return authorRepository.findById(id).map(existingAuthor -> {
            if(author.getName() != null){
                existingAuthor.setName(author.getName());
            }
            if(author.getCountry() != null){
                existingAuthor.setCountry(author.getCountry());
            }
            if(author.getSurname()!=null){
                existingAuthor.setSurname(author.getSurname());
            }
           return authorRepository.save(existingAuthor);
        });
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorProjection> findAllAuthorNames() {
        return authorRepository.findAllBy();
    }
}
