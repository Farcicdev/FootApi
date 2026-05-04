package farcic.dev.footApi.mapper;

import farcic.dev.footApi.dto.request.PlayerRequestDto;
import farcic.dev.footApi.dto.response.PlayerResponseDetails;
import farcic.dev.footApi.dto.response.PlayerResponseDto;
import farcic.dev.footApi.entity.Player;
import farcic.dev.footApi.entity.PositionEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "club.id", source = "clubId")
    Player toEntity(PlayerRequestDto playerDto);

    @Mapping(target = "position", source = "position", qualifiedByName = "EnumToString")
    PlayerResponseDto toResponse(Player player);

    @Named("EnumToString")
    default String enumToString(PositionEnum position) {
        return position != null ? position.name() : null;
    }

    @Mapping(target = "position", source = "position", qualifiedByName = "EnumToString")
    PlayerResponseDetails toDetailsResponse(Player player);
}
