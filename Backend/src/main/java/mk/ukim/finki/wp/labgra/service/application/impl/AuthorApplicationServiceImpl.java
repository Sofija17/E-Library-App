package mk.ukim.finki.wp.labgra.service.application.impl;

import jdk.jfr.Category;
import mk.ukim.finki.wp.labgra.dto.CreateAuthorDto;
import mk.ukim.finki.wp.labgra.dto.DisplayAuthorDto;
import mk.ukim.finki.wp.labgra.events.AuthorChangedEvent;
import mk.ukim.finki.wp.labgra.events.AuthorCreatedEvent;
import mk.ukim.finki.wp.labgra.events.AuthorDeletedEvent;
import mk.ukim.finki.wp.labgra.model.domain.Author;
import mk.ukim.finki.wp.labgra.model.domain.Country;
import mk.ukim.finki.wp.labgra.model.projections.AuthorProjection;
//import mk.ukim.finki.wp.labgra.model.views.AuthorsPerCountryView;
//import mk.ukim.finki.wp.labgra.repository.AuthorsPerCountryViewRepository;
import mk.ukim.finki.wp.labgra.service.application.AuthorApplicationService;
import mk.ukim.finki.wp.labgra.service.domain.AuthorService;
import mk.ukim.finki.wp.labgra.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;
    private final ApplicationEventPublisher eventPublisher;
//    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;


    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService,
                                        ApplicationEventPublisher eventPublisher
                                      //  ,AuthorsPerCountryViewRepository authorsPerCountryViewRepository
                                          ) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.eventPublisher = eventPublisher;
       // this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto authorDto) {
        Optional<Country> country = countryService.findById(authorDto.countryId());

        if (country.isPresent()) {
            return authorService.save(authorDto.toAuthor(country.get()))
                    .map(author -> {
                        eventPublisher.publishEvent(new AuthorCreatedEvent(author));
                        return DisplayAuthorDto.from(author);
                    });
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(author -> DisplayAuthorDto.from(author));
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto authorDto) {
        Optional<Country> country = countryService.findById(authorDto.countryId());

        if (country.isPresent()) {
            return authorService.update(id, authorDto.toAuthor(country.get()))
                    .map(author -> {
                        eventPublisher.publishEvent(new AuthorChangedEvent(author));
                        return DisplayAuthorDto.from(author);
                    });
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        Optional<Author> author = authorService.findById(id);
        //author.ifPresent(a -> eventPublisher.publishEvent(new AuthorDeletedEvent(a)));
        authorService.deleteById(id);
    }

//    @Override
//    public List<AuthorsPerCountryView> findAllAuthorsPerCountry() {
//        return authorsPerCountryViewRepository.findAll();
//    }

//    @Override
//    public void refreshMaterializedView() {
//        this.refreshMaterializedView();
//    }

    @Override
    public List<AuthorProjection> findAllAuthorNames() {
        return authorService.findAllAuthorNames();
    }


}
