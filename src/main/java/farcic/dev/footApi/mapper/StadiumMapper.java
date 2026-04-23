package farcic.dev.footApi.mapper;

import farcic.dev.footApi.dto.StadiumRequestDto;
import farcic.dev.footApi.dto.StadiumResponseDto;
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

    public void updateEntity(Stadium stadium, StadiumRequestDto requestDto) {
        stadium.setName(requestDto.name());
        stadium.setCity(requestDto.city());
        stadium.setCapacity(requestDto.capacity());
        stadium.setUrlImg(requestDto.urlImg());
    }
}
