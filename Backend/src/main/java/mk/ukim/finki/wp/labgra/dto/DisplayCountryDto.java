package mk.ukim.finki.wp.labgra.dto;

import mk.ukim.finki.wp.labgra.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDto(
        Long id,
        String name,
        String continent
) {

    public static DisplayCountryDto from (Country country){
        return new DisplayCountryDto(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }

    public static List<DisplayCountryDto> from (List<Country> countries){
        return countries.stream().map(country -> DisplayCountryDto.from(country)).collect(Collectors.toList());
    }

    public Country toCountry (){
        return new Country(name,continent);
    }
}
