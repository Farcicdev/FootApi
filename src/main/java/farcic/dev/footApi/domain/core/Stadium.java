package farcic.dev.footApi.domain.core;

public record Stadium(
        Long id,
        String name,
        String city,
        Integer capacity,
        String urlImg
) {
}
