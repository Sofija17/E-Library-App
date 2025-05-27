package mk.ukim.finki.wp.labgra.service.application.impl;

import mk.ukim.finki.wp.labgra.dto.DisplayUserLogDto;
import mk.ukim.finki.wp.labgra.service.application.UserLogsApplicationService;
import mk.ukim.finki.wp.labgra.service.domain.UserLogsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogsApplicationServiceImpl  implements UserLogsApplicationService {

    private final UserLogsService userLogsService;


    public UserLogsApplicationServiceImpl(UserLogsService userLogsService) {
        this.userLogsService = userLogsService;
    }

    @Override
    public List<DisplayUserLogDto> findAll() {
        return this.userLogsService.findAll().stream().map(DisplayUserLogDto::from).toList();
    }
}
