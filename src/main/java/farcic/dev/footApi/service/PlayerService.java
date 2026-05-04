package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.request.PlayerRequestDto;
import farcic.dev.footApi.dto.response.PlayerResponseDetails;
import farcic.dev.footApi.dto.response.PlayerResponseDto;
import farcic.dev.footApi.entity.Player;
import farcic.dev.footApi.mapper.PlayerMapper;
import farcic.dev.footApi.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper mapper;

    private final ClubService clubService;

    public Page<PlayerResponseDto> findall(Pageable pageable){
        return playerRepository.findAll(pageable)
                .map(e -> mapper.toResponse(e));
    }

    public PlayerResponseDetails findById(Long id){
        return playerRepository.findById(id)
                .map(e -> mapper.toDetailsResponse(e))
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public PlayerResponseDetails save(PlayerRequestDto request){
        Player entity = mapper.toEntity(request);
        if (Objects.nonNull(entity.getClub())) {
            entity.setClub(clubService.validarIdClub(entity.getClub().getId()));
        }
        Player saved = playerRepository.save(entity);
        return mapper.toDetailsResponse(saved);
    }

    public List<PlayerResponseDto> findByClubId(Long Id) {
        return playerRepository.findByClubId(Id).stream()
                .map(e -> mapper.toResponse(e))
                .toList();
    }
}
