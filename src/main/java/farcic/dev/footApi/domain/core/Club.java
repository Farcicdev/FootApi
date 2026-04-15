package farcic.dev.footApi.domain.core;

import java.time.LocalDateTime;

public record Club(
        Long id,
        String name,
        LocalDateTime founded,
        String urlImg,
        LocalDateTime createdAt,
        Boolean active,
        Long stadiumId
) {
}
