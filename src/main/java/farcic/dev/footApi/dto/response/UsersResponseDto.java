package farcic.dev.footApi.dto.response;

import java.util.List;

public record UsersResponseDto(

        Long id,
        String name,
        String email,
        List<String> scopes


) {
}
