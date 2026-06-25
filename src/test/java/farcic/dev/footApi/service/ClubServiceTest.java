package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.request.ClubRequestDto;
import farcic.dev.footApi.dto.response.ClubDetatilsResponse;
import farcic.dev.footApi.dto.response.ClubResponseDto;
import farcic.dev.footApi.dto.response.StadiumResponseDto;
import farcic.dev.footApi.entity.Club;
import farcic.dev.footApi.entity.Stadium;
import farcic.dev.footApi.mapper.ClubMapper;
import farcic.dev.footApi.repository.ClubRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
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
                .stadiumId(10L)
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

        ClubDetatilsResponse response = new ClubDetatilsResponse(
                1L,
                "Teste",
                LocalDate.of(2020, 1, 1),
                "http://teste.com/img.png",
                StadiumResponseDto.builder()
                        .id(10L)
                        .name("Teste Stadium")
                        .city("Teste City")
                        .capacity(50000)
                        .build()
        );

        Mockito.when(mapper.toEntity(request)).thenReturn(teste);
        Mockito.when(stadiumService.findById(stadium.getId())).thenReturn(stadium);
        Mockito.when(clubRepository.save(teste)).thenReturn(teste);
        Mockito.when(mapper.toDetailsResponseDto(teste)).thenReturn(response);
        //Action
        ClubDetatilsResponse execute = service.save(request);

        //Assert
        Assertions.assertEquals(response, execute);
        Assertions.assertEquals(stadium, teste.getStadium());
        Mockito.verify(stadiumService).findById(10L);
        Mockito.verify(clubRepository).save(teste);

    }

    @Test
    void findAll() {
        //arrange
        Club club = Club.builder()
                .id(1L)
                .name("Teste")
                .founded(LocalDate.of(2020, 1, 1))
                .urlImg("http://teste.com/img.png")
                .stadium(Stadium.builder()
                        .id(10L)
                        .name("Teste Stadium")
                        .city("Teste City")
                        .capacity(50000)
                        .build())
                .build();

        Club club2 = Club.builder()
                .id(2L)
                .name("Teste 2")
                .founded(LocalDate.of(2021, 1, 1))
                .urlImg("http://teste.com/img2.png")
                .stadium(Stadium.builder()
                        .id(11L)
                        .name("Teste Stadium 2")
                        .city("Teste City 2")
                        .capacity(60000)
                        .build())
                .build();

        List<Club> clubs = List.of(club, club2);

        List<ClubResponseDto> responseDtos = List.of(
                new ClubResponseDto(1L, "Teste", LocalDate.of(2020, 1, 1), "http://teste.com/img.png"),
                new ClubResponseDto(2L, "Teste 2", LocalDate.of(2021, 1, 1), "http://teste.com/img2.png")
        );

    }

    @Test
    void findById() {
        //Arrenge
        Club club = Club.builder()
                .id(1L)
                .name("Teste")
                .founded(LocalDate.of(2020, 1, 1))
                .urlImg("http://teste.com/img.png")
                .stadium(Stadium.builder()
                        .id(10L)
                        .name("Teste Stadium")
                        .city("Teste City")
                        .capacity(50000)
                        .build())
                .build();

        ClubDetatilsResponse response = new ClubDetatilsResponse(
                1L,
                "Teste",
                LocalDate.of(2020, 1, 1),
                "http://teste.com/img.png",
                StadiumResponseDto.builder()
                        .id(10L)
                        .name("Teste Stadium")
                        .city("Teste City")
                        .capacity(50000)
                        .build()
        );

        Mockito.when(clubRepository.findById(1L)).thenReturn(Optional.of(club));
        Mockito.when(mapper.toDetailsResponseDto(club)).thenReturn(response);

        //Action
        ClubDetatilsResponse execute = service.findById(1L);

        //Assert
        Assertions.assertEquals(response, execute);
        Mockito.verify(clubRepository).findById(1L);
        Mockito.verify(mapper).toDetailsResponseDto(club);
    }

    @Test
    void validarIdClub() {
    }
}
