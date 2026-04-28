package farcic.dev.footApi.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String message,
        int status,
        LocalDateTime timestamp
) {
}
