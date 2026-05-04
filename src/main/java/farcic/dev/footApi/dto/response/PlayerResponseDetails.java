package farcic.dev.footApi.dto.response;

public record PlayerResponseDetails(

        String name,
        String position,
        int shirtNumber,
        String urlImg,
        ClubResponseDto club
) {
}
