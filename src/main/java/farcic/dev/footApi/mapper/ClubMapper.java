package farcic.dev.footApi.mapper;

import farcic.dev.footApi.dto.request.ClubRequestDto;
import farcic.dev.footApi.dto.response.ClubResponseDto;
import farcic.dev.footApi.entity.Club;
import farcic.dev.footApi.entity.Stadium;
import org.springframework.stereotype.Component;

@Component
public class ClubMapper {

    public Club toEntity(ClubRequestDto club) {
        return Club.builder()
                .name(club.name())
                .founded(club.founded())
                .urlImg(club.urlImg())
                .stadium(Stadium.builder()
                        .id(club.stadiumId())
                        .build())
                .build();
    }

    public ClubResponseDto toResponseDto(Club club) {
        return ClubResponseDto.builder()
                .id(club.getId())
                .name(club.getName())
                .founded(club.getFounded())
                .urlImg(club.getUrlImg())
                .build();
    }

}
