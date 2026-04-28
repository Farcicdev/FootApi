package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.request.StadiumRequestDto;
import farcic.dev.footApi.dto.response.StadiumResponseDto;
import farcic.dev.footApi.entity.Stadium;
import farcic.dev.footApi.exception.ConflictException;
import farcic.dev.footApi.mapper.StadiumMapper;
import farcic.dev.footApi.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository stadiumRepository;
    private final StadiumMapper stadiumMapper;

    public Page<StadiumResponseDto> listAll(Pageable pageable) {
        return stadiumRepository.findAll(pageable)
                .map(stadiumMapper::toResponseDto);
    }

    public StadiumResponseDto createStadium(StadiumRequestDto requestDto) {
        validateStadiumName(requestDto.name());
        Stadium entity = stadiumMapper.toEntity(requestDto);
        Stadium saved = stadiumRepository.save(entity);
        return stadiumMapper.toResponseDto(saved);
    }

    public void validateStadiumName(String name) {
        if (stadiumRepository.existsByName(name)) {
            throw new ConflictException("Ja Existe um estadio com o nome: " + name);
        }
    }
}
