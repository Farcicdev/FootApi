package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.request.LoginRequestDto;
import farcic.dev.footApi.dto.response.LoginResponseDto;
import farcic.dev.footApi.entity.Users;
import farcic.dev.footApi.repository.LoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @InjectMocks
    LoginService loginService;
    @Mock
    LoginRepository loginRepository;
    @Mock
    PasswordEncoder encoder;
    @Mock
    JwtEncoder jwtEncoder;

    @Test
    void login() {
        Users user = Users.builder()
                .id(1L)
                .name("Augusto")
                .email("test@test.com.br")
                .password("senhaCriptografada")
                .scopes(List.of())
                .build();

        LoginRequestDto requestDto = LoginRequestDto
                .builder()
                .email("test@test.com.br")
                .password("test123")
                .build();

        Mockito.when(loginRepository.findByEmail(requestDto.email()))
                .thenReturn(Optional.of(user));
        Mockito.when(encoder.matches(requestDto.password(), user.getPassword()))
                .thenReturn(true);
        Jwt jwt = Mockito.mock(Jwt.class);

        Mockito.when(jwtEncoder.encode(Mockito.any()))
                .thenReturn(jwt);

        Mockito.when(jwt.getTokenValue())
                .thenReturn("test123");

        LoginResponseDto response = loginService.login(requestDto);

        assertEquals("test123", response.token());
        assertEquals(3600L, response.expiresIn());

    }
}