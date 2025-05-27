package mk.ukim.finki.wp.labgra.service.application.impl;

import mk.ukim.finki.wp.labgra.dto.CreateCountryDto;
import mk.ukim.finki.wp.labgra.dto.DisplayCountryDto;
import mk.ukim.finki.wp.labgra.model.domain.Country;
//import mk.ukim.finki.wp.labgra.model.views.AuthorsPerCountryView;
//import mk.ukim.finki.wp.labgra.repository.AuthorsPerCountryViewRepository;
import mk.ukim.finki.wp.labgra.service.application.CountryApplicationService;
import mk.ukim.finki.wp.labgra.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    public final CountryService countryService;
//    public final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;



    public CountryApplicationServiceImpl(CountryService countryService
                                   //      ,AuthorsPerCountryViewRepository authorsPerCountryViewRepository
                                         ,ApplicationEventPublisher applicationEventPublisher) {
        this.countryService = countryService;
        //this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;

    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto countryDto) {
       return countryService.save(countryDto.toCountry())
               .map(country -> DisplayCountryDto.from(country));
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(country -> DisplayCountryDto.from(country));
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto) {
        return countryService.update(id, countryDto.toCountry()).map(country -> DisplayCountryDto.from(country));
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }

}
