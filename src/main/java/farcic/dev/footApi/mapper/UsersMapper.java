package farcic.dev.footApi.mapper;

import farcic.dev.footApi.dto.request.UsersRequestDto;
import farcic.dev.footApi.dto.response.UsersResponseDto;
import farcic.dev.footApi.entity.Scopes;
import farcic.dev.footApi.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(target = "scopes", source = "scopes", qualifiedByName = "mapScopeIdsToScopeEntities")
    Users toEntity(UsersRequestDto requestDto);

    @Mapping(target = "scopes", source = "scopes", qualifiedByName = "mapScopeIdsToStringScope")
    UsersResponseDto toResponseDto(Users entity);

    @Named("mapScopeIdsToScopeEntities")
    default List<Scopes> scopeIdsToScopes(List<Long> scopeIds) {
        if (scopeIds == null) {
            return null;
        }
        return scopeIds.stream()
                .map(id -> Scopes.builder().id(id).build())
                .toList();
    }


    @Named("mapScopeIdsToStringScope")
    default List<String> scopesToScopeIds(List<Scopes> scopes) {
        if (scopes == null) return List.of();
        return scopes.stream()
                .map(s -> s.getName())
                .toList();
    }

}
