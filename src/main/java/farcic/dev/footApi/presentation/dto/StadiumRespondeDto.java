package farcic.dev.footApi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record StadiumRespondeDto(
        Long id,
        String name,
        String city,
        Integer capacity,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String urlImg
) {
}
