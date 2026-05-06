package farcic.dev.footApi.dto.response;

import lombok.Builder;

@Builder
public record LoginResponseDto (

        String token,
        Long expiresIn

){
}
