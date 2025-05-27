package mk.ukim.finki.wp.labgra.service.domain.impl;

import mk.ukim.finki.wp.labgra.model.domain.Country;
import mk.ukim.finki.wp.labgra.model.domain.UserLogs;
import mk.ukim.finki.wp.labgra.repository.UserLogsRepository;
import mk.ukim.finki.wp.labgra.security.JwtHelper;
import mk.ukim.finki.wp.labgra.service.domain.UserLogsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLogsServiceImpl implements UserLogsService {

    private final UserLogsRepository userLogsRepository;


    public UserLogsServiceImpl(UserLogsRepository userLogsRepository) {
        this.userLogsRepository = userLogsRepository;
    }

    @Override
    public List<UserLogs> findAll() {
        return userLogsRepository.findAll();
    }

    @Override
    public Optional<UserLogs> save(UserLogs userLog) {
        return Optional.of(userLogsRepository.save(userLog));
    }

}
