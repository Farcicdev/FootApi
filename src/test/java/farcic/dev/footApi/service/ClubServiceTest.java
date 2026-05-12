package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.request.ClubRequestDto;
import farcic.dev.footApi.dto.response.ClubDetatilsResponse;
import farcic.dev.footApi.dto.response.StadiumResponseDto;
import farcic.dev.footApi.entity.Club;
import farcic.dev.footApi.entity.Stadium;
import farcic.dev.footApi.mapper.ClubMapper;
import farcic.dev.footApi.repository.ClubRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;

class ClubServiceTest {

    @InjectMocks
    ClubService service;

    @Mock
    ClubRepository clubRepository;
    @Mock
    ClubMapper mapper;
    @Mock
    StadiumService stadiumService;

    @Test
    void save() {
        //Arrenge
        ClubRequestDto request = ClubRequestDto.builder()
                .name("Teste")
                .founded(LocalDate.of(2020, 1, 1))
                .urlImg("http://teste.com/img.png")
                .stadiumId(1L)
                .build();
        Club teste = Club.builder()
                .id(1L)
                .name("Teste")
                .founded(LocalDate.of(2020, 1, 1))
                .urlImg("http://teste.com/img.png")
                .stadium(Stadium.builder().id(10L).build())
                .build();

        Stadium stadium = Stadium.builder()
                .id(10L)
                .name("Teste Stadium")
                .city("Teste City")
                .capacity(50000)
                .build();

        Mockito.when(mapper.toEntity(request)).thenReturn(teste);
        Mockito.when(stadiumService.findById(stadium.getId())).thenReturn(stadium);
        //Action
        StadiumResponseDto execute = stadiumService.createStadium(request);

    }

    @Test
    void findAll() {

    }

    @Test
    void findById() {
    }

    @Test
    void validarIdClub() {
    }
}