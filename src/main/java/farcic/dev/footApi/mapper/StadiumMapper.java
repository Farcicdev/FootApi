package farcic.dev.footApi.mapper;

import farcic.dev.footApi.dto.request.StadiumRequestDto;
import farcic.dev.footApi.dto.response.StadiumResponseDto;
import farcic.dev.footApi.entity.Stadium;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StadiumMapper {

    StadiumResponseDto toResponseDto(Stadium stadium);

    Stadium toEntity(StadiumRequestDto requestDto);

}
