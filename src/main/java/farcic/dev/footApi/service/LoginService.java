package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.request.LoginRequestDto;
import farcic.dev.footApi.dto.response.LoginResponseDto;
import farcic.dev.footApi.entity.Users;
import farcic.dev.footApi.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder encoder;
    private final JwtEncoder jwtEncoder;

    public LoginResponseDto login(LoginRequestDto requestDto){
        Optional<Users> optLogin = loginRepository.findByEmail(requestDto.email());

        if (optLogin.isEmpty() || !isPasswordValid(requestDto.password(), optLogin.get().getPassword())) {
            throw new UsernameNotFoundException("Usuario ou senha inválidos");
        }
        Users savedUser = optLogin.get();
        List<String> scopes = savedUser.getScopes().stream()
                .map(scope -> scope.getName())
                .toList();
        long expirationTime = 3600L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("footApi")
                .subject(savedUser.getName())
                .expiresAt(Instant.now().plusSeconds(expirationTime))
                .issuedAt(Instant.now())
                .claim("email", savedUser.getEmail())
                .claim("scopes", scopes)
                .build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return LoginResponseDto.builder()
                .token(token)
                .expiresIn(expirationTime)
                .build();
    }

    private boolean isPasswordValid(String password, String savedPassword) {
        return encoder.matches(password, savedPassword);
    }

}
