package farcic.dev.footApi.domain.core;

public record Player(

        Long id,
        String name,
        PositionEnum position,
        int shirtNumber,
        String urlImg,
        Long clubId

) {
}
