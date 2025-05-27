package mk.ukim.finki.wp.labgra.dto;


import mk.ukim.finki.wp.labgra.model.domain.User;
import mk.ukim.finki.wp.labgra.model.enums.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}
