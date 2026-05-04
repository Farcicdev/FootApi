package farcic.dev.footApi.mapper;

import farcic.dev.footApi.dto.request.ClubRequestDto;
import farcic.dev.footApi.dto.response.ClubDetatilsResponse;
import farcic.dev.footApi.dto.response.ClubResponseDto;
import farcic.dev.footApi.entity.Club;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClubMapper {

    @Mapping(target = "stadium.id", source = "stadiumId")
    Club toEntity(ClubRequestDto request);

    ClubResponseDto toResponseDto(Club club);

    ClubDetatilsResponse toDetailsResponseDto(Club club);
}
