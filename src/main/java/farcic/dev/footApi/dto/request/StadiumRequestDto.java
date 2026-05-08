package farcic.dev.footApi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record StadiumRequestDto(
        @NotBlank
        String name,
        @NotBlank
        String city,
        Integer capacity,
        String urlImg
) {
}
