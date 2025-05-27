package mk.ukim.finki.wp.labgra.service.domain;

import mk.ukim.finki.wp.labgra.model.domain.User;
import mk.ukim.finki.wp.labgra.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);
}
