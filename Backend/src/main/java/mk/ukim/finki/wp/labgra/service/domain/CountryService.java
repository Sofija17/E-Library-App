package mk.ukim.finki.wp.labgra.service.domain;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.wp.labgra.model.domain.Country;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> save(Country country);

    Optional<Country> findById(Long id);

    Optional<Country> update(Long id, Country country);

    void deleteById(Long id);
}
