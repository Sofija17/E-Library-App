package mk.ukim.finki.wp.labgra.service.application;

import mk.ukim.finki.wp.labgra.dto.CreateUserDto;
import mk.ukim.finki.wp.labgra.dto.DisplayUserDto;
import mk.ukim.finki.wp.labgra.dto.LoginResponseDto;
import mk.ukim.finki.wp.labgra.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);


    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);


    Optional<DisplayUserDto> findByUsername(String username);
}
