package mk.ukim.finki.wp.labgra.service.application;

import mk.ukim.finki.wp.labgra.dto.DisplayUserLogDto;

import java.util.List;

public interface UserLogsApplicationService {

    List<DisplayUserLogDto> findAll();

}
