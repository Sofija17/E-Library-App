package mk.ukim.finki.wp.labgra.dto;

import mk.ukim.finki.wp.labgra.model.domain.Author;
import mk.ukim.finki.wp.labgra.model.domain.Country;

public record CreateAuthorDto(
        String name,
        String surname,
        Long countryId
) {

    public static CreateAuthorDto from (Author author){
        return new CreateAuthorDto(
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }

    public Author toAuthor (Country country){
        return new Author(name,surname,country);
    }

}
