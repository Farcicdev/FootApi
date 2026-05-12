package farcic.dev.footApi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClubRequestDto(
        @NotBlank
        String name,
        @NotNull
        LocalDate founded,
        String urlImg,
        Long stadiumId
) {
}
