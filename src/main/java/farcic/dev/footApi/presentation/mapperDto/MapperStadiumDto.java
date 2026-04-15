package farcic.dev.footApi.presentation.mapperDto;

import farcic.dev.footApi.domain.core.Stadium;
import farcic.dev.footApi.presentation.dto.StadiumRespondeDto;
import org.springframework.stereotype.Component;

@Component
public class MapperStadiumDto {

    public StadiumRespondeDto toResponse(Stadium stadium) {
        return StadiumRespondeDto.builder()
                .id(stadium.id())
                .name(stadium.name())
                .city(stadium.city())
                .capacity(stadium.capacity())
                .urlImg(stadium.urlImg())
                .build();
    }

}
