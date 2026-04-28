package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.request.ClubRequestDto;
import farcic.dev.footApi.dto.response.ClubResponseDto;
import farcic.dev.footApi.entity.Club;
import farcic.dev.footApi.mapper.ClubMapper;
import farcic.dev.footApi.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubMapper mapper;

    public Page<ClubResponseDto> findAll(Pageable pageable) {
        return clubRepository.findAll(pageable)
                .map(e -> mapper.toResponseDto(e));
    }

    public ClubResponseDto findById(Long id) {
        return clubRepository.findById(id)
                .map(e -> mapper.toResponseDto(e))
                .orElseThrow(() -> new RuntimeException("Club not found"));
    }

    public ClubResponseDto save(ClubRequestDto request){
        Club club = mapper.toEntity(request);
        Club savedClub = clubRepository.save(club);
        return mapper.toResponseDto(savedClub);
    }

}
