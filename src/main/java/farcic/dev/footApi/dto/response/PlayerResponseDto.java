package farcic.dev.footApi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public record PlayerResponseDto(

        String name,
        String position,
        int shirtNumber,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String urlImg

) {
}
