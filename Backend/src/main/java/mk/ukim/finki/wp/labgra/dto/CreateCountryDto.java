package mk.ukim.finki.wp.labgra.dto;

import mk.ukim.finki.wp.labgra.model.domain.Country;

public record CreateCountryDto(
        String name,
        String continent
) {

    public static CreateCountryDto from (Country country){
        return new CreateCountryDto(
                country.getName(),
                country.getContinent()
        );
    }

    public Country toCountry(){
        return new Country(name,continent);
    }

}
