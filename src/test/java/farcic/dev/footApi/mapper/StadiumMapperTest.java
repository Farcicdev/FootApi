package farcic.dev.footApi.mapper;

import farcic.dev.footApi.dto.request.StadiumRequestDto;
import farcic.dev.footApi.dto.response.StadiumResponseDto;
import farcic.dev.footApi.entity.Stadium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class StadiumMapperTest {

    private final StadiumMapper mapper = Mappers.getMapper(StadiumMapper.class);

    @Test
    @DisplayName("Given a Stadium entity, when toResponseDto is called, then it should return a StadiumResponseDto with the same values")
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

        Assertions.assertEquals(stadiumA.getId(), responseDto.id());
        Assertions.assertEquals(stadiumA.getName(), responseDto.name());
        Assertions.assertEquals(stadiumA.getCity(), responseDto.city());
        Assertions.assertEquals(stadiumA.getCapacity(), responseDto.capacity());
        Assertions.assertEquals(stadiumA.getUrlImg(), responseDto.urlImg());
    }

    @Test
    void toEntity() {

        StadiumRequestDto request = StadiumRequestDto.builder()
                .name("Stadium A")
                .city("City A")
                .capacity(50000)
                .urlImg("http://example.com/stadiumA.jpg")
                .build();


        Stadium entity = mapper.toEntity(request);

        //then
        Assertions.assertNotNull(entity);

        Assertions.assertEquals(request.name(), entity.getName());
        Assertions.assertEquals(request.city(), entity.getCity());
        Assertions.assertEquals(request.capacity(), entity.getCapacity());
        Assertions.assertEquals(request.urlImg(), entity.getUrlImg());
    }
}