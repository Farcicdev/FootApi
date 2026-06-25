package farcic.dev.footApi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginRequestDto(

        @NotNull(message = "Email cannot be null")
        @Email(message = "Invalid email format")
        String email,
        @NotNull(message = "Password cannot be null")
        String password

) {
}
