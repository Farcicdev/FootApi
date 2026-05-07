package farcic.dev.footApi.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UpdatePasswordRequest (

        @NotEmpty
        String oldPassword,
        @NotEmpty
        String newPassword,
        @NotEmpty
        String confirmPassword

) {
}
