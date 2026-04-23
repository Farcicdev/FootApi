package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.StadiumRequestDto;
import farcic.dev.footApi.dto.StadiumResponseDto;
import farcic.dev.footApi.entity.Stadium;
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

    public StadiumResponseDto create(StadiumRequestDto requestDto) {
        Stadium stadium = stadiumMapper.toEntity(requestDto);
        Stadium savedStadium = stadiumRepository.save(stadium);
        return stadiumMapper.toResponseDto(savedStadium);
    }
}
