package mk.ukim.finki.wp.labgra.service.domain;

import mk.ukim.finki.wp.labgra.model.domain.UserLogs;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserLogsService {

    List<UserLogs> findAll();

    Optional<UserLogs> save(UserLogs userLog);


}
