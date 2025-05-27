package mk.ukim.finki.wp.labgra.service.application;

import mk.ukim.finki.wp.labgra.dto.CreateCountryDto;
import mk.ukim.finki.wp.labgra.dto.DisplayCountryDto;
import mk.ukim.finki.wp.labgra.model.domain.Country;
//import mk.ukim.finki.wp.labgra.model.views.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService
{
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> save(CreateCountryDto countryDto);

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto );

    void deleteById(Long id);


}
