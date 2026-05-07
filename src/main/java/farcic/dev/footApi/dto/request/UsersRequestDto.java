package farcic.dev.footApi.dto.request;


import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record UsersRequestDto (
        @NotEmpty
        String name,
        @NotEmpty
        String email,
        @NotEmpty
        String password,
        @NotEmpty
        List<Long> scopes
){
}
