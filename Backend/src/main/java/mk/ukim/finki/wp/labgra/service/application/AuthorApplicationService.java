package mk.ukim.finki.wp.labgra.service.application;

import mk.ukim.finki.wp.labgra.dto.CreateAuthorDto;
import mk.ukim.finki.wp.labgra.dto.DisplayAuthorDto;
import mk.ukim.finki.wp.labgra.model.projections.AuthorProjection;
//import mk.ukim.finki.wp.labgra.model.views.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> findAll();

    Optional<DisplayAuthorDto> save(CreateAuthorDto authorDto);

    Optional<DisplayAuthorDto> findById(Long id);

    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto authorDto);

    void deleteById(Long id);
//    List<AuthorsPerCountryView> findAllAuthorsPerCountry();
//    void refreshMaterializedView();

    List<AuthorProjection> findAllAuthorNames();

}
