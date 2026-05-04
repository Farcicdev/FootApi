package farcic.dev.footApi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlayerRequestDto(

        @NotBlank
        String name,
        @NotNull
        String position,
        @NotNull
        int shirtNumber,
        String urlImg,
        @NotNull
        Long clubId

) {
}
