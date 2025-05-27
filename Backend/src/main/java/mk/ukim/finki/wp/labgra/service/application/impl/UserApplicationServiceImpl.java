package mk.ukim.finki.wp.labgra.service.application.impl;

import mk.ukim.finki.wp.labgra.dto.CreateUserDto;
import mk.ukim.finki.wp.labgra.dto.DisplayUserDto;
import mk.ukim.finki.wp.labgra.dto.LoginResponseDto;
import mk.ukim.finki.wp.labgra.dto.LoginUserDto;
import mk.ukim.finki.wp.labgra.model.domain.User;
import mk.ukim.finki.wp.labgra.model.domain.UserLogs;
import mk.ukim.finki.wp.labgra.security.JwtHelper;
import mk.ukim.finki.wp.labgra.service.application.UserApplicationService;
import mk.ukim.finki.wp.labgra.service.domain.UserLogsService;
import mk.ukim.finki.wp.labgra.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;
    private final UserLogsService userLogsService;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper, UserLogsService userLogsService) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
        this.userLogsService = userLogsService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        Date expiredAt = jwtHelper.extractExpiration(token);
        Date issuedAt = jwtHelper.extractIssuedAt(token);

        userLogsService.save(new UserLogs(token,loginUserDto.username(),expiredAt,issuedAt));

        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }
}
