package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.StadiumRequestDto;
import farcic.dev.footApi.dto.StadiumResponseDto;
import farcic.dev.footApi.entity.Stadium;
import farcic.dev.footApi.exception.ConflictException;
import farcic.dev.footApi.exception.ResourceNotFoundException;
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

    public StadiumResponseDto findById(Long id) {
        return stadiumMapper.toResponseDto(findEntityById(id));
    }

    public StadiumResponseDto create(StadiumRequestDto requestDto) {
        validateNameUniqueness(requestDto.name(), null);
        Stadium stadium = stadiumMapper.toEntity(requestDto);
        Stadium savedStadium = stadiumRepository.save(stadium);
        return stadiumMapper.toResponseDto(savedStadium);
    }

    public StadiumResponseDto update(Long id, StadiumRequestDto requestDto) {
        validateNameUniqueness(requestDto.name(), id);
        Stadium stadium = findEntityById(id);
        stadiumMapper.updateEntity(stadium, requestDto);
        Stadium updatedStadium = stadiumRepository.save(stadium);
        return stadiumMapper.toResponseDto(updatedStadium);
    }

    public void delete(Long id) {
        Stadium stadium = findEntityById(id);
        stadiumRepository.delete(stadium);
    }

    private Stadium findEntityById(Long id) {
        return stadiumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estadio nao encontrado com id " + id));
    }

    private void validateNameUniqueness(String name, Long id) {
        boolean alreadyExists = id == null
                ? stadiumRepository.existsByName(name)
                : stadiumRepository.existsByNameAndIdNot(name, id);

        if (alreadyExists) {
            throw new ConflictException("Ja existe um estadio com o nome " + name);
        }
    }
}
