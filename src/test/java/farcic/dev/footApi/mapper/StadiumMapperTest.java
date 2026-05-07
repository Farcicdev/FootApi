package farcic.dev.footApi.mapper;

import farcic.dev.footApi.dto.response.StadiumResponseDto;
import farcic.dev.footApi.entity.Stadium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class StadiumMapperTest {

    private final StadiumMapper mapper = Mappers.getMapper(StadiumMapper.class);

    @Test
    void toResponseDto() {
        //Given
        Stadium stadiumA = Stadium.builder()
                .id(1L)
                .name("Stadium A")
                .city("City A")
                .capacity(50000)
                .urlImg("http://example.com/stadiumA.jpg")
                .build();
        //actions
        StadiumResponseDto responseDto = mapper.toResponseDto(stadiumA);
        //Then
        Assertions.assertNotNull(responseDto);
    }

    @Test
    void toEntity() {
    }
}