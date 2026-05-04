package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.request.ClubRequestDto;
import farcic.dev.footApi.dto.response.ClubDetatilsResponse;
import farcic.dev.footApi.dto.response.ClubResponseDto;
import farcic.dev.footApi.entity.Club;
import farcic.dev.footApi.exception.ConflictException;
import farcic.dev.footApi.mapper.ClubMapper;
import farcic.dev.footApi.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubMapper mapper;

    private final StadiumService validarIdStadium;

    public Page<ClubResponseDto> findAll(Pageable pageable) {
        return clubRepository.findAll(pageable)
                .map(e -> mapper.toResponseDto(e));
    }

    public ClubDetatilsResponse findById(Long id) {
        return clubRepository.findById(id)
                .map(e -> mapper.toDetailsResponseDto(e))
                .orElseThrow(() -> new RuntimeException("Club not found"));
    }

    public ClubDetatilsResponse save(ClubRequestDto request) {
        Club club = mapper.toEntity(request);

        if (Objects.nonNull(club.getStadium())) {
            club.setStadium(validarIdStadium.findById(club.getStadium().getId()));
        }
        Club savedClub = clubRepository.save(club);
        return mapper.toDetailsResponseDto(savedClub);
    }

    public Club validarIdClub(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new ConflictException("Club not found"));
    }

}
