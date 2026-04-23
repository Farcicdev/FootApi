package farcic.dev.footApi.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDto(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> fields
) {
}
