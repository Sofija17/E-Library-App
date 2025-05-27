package mk.ukim.finki.wp.labgra.dto;

import mk.ukim.finki.wp.labgra.model.domain.Country;
import mk.ukim.finki.wp.labgra.model.domain.UserLogs;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayUserLogDto(
        Long id,
        String token,
        String username,
        Date issuedAt,
        Date expiredAt) {

    public static DisplayUserLogDto from (UserLogs userLogs){
        return new DisplayUserLogDto(
                userLogs.getId(),
                userLogs.getToken(),
                userLogs.getUsername(),
                userLogs.getIssuedAt(),
                userLogs.getExpiredAt()
        );
    }

    public static List<DisplayUserLogDto> from (List<UserLogs> userLogs){
        return userLogs.stream().map(log -> DisplayUserLogDto.from(log)).collect(Collectors.toList());
    }

    public UserLogs toUserLog (){
        return new UserLogs(token,username,issuedAt,expiredAt);
    }
}
