package farcic.dev.footApi.mapper;

import farcic.dev.footApi.dto.request.StadiumRequestDto;
import farcic.dev.footApi.dto.response.StadiumResponseDto;
import farcic.dev.footApi.entity.Stadium;
import org.springframework.stereotype.Component;

@Component
public class StadiumMapper {

    public StadiumResponseDto toResponseDto(Stadium stadium) {
        return StadiumResponseDto.builder()
                .id(stadium.getId())
                .name(stadium.getName())
                .city(stadium.getCity())
                .capacity(stadium.getCapacity())
                .urlImg(stadium.getUrlImg())
                .build();
    }

    public Stadium toEntity(StadiumRequestDto requestDto) {
        return Stadium.builder()
                .name(requestDto.name())
                .city(requestDto.city())
                .capacity(requestDto.capacity())
                .urlImg(requestDto.urlImg())
                .build();
    }

}
