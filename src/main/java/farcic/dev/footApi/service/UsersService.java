package farcic.dev.footApi.service;

import farcic.dev.footApi.dto.request.UpdatePasswordRequest;
import farcic.dev.footApi.dto.request.UsersRequestDto;
import farcic.dev.footApi.dto.response.UsersResponseDto;
import farcic.dev.footApi.entity.Scopes;
import farcic.dev.footApi.entity.Users;
import farcic.dev.footApi.mapper.UsersMapper;
import farcic.dev.footApi.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository repository;
    private final UsersMapper mapper;
    private final ScopeService scopeService;
    private final PasswordEncoder passwordEncoder;

    //Criar
    @Transactional
    public UsersResponseDto create(UsersRequestDto request) {

        if (repository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        List<Scopes> scopes = request.scopes().stream()
                .map(s -> scopeService.findById(s))
                .toList();

        Users entity = mapper.toEntity(request);
        entity.setScopes(scopes);
        entity.setPassword(passwordEncoder.encode(request.password()));
        entity.setActive(true);
        Users saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    //atualizar senha
    public void updatePassword(Long id, UpdatePasswordRequest request) {
        Users user = repository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));
        validateOldPassword(request.oldPassword(), user.getPassword());

        validateNewPassword(
                request.newPassword(),
                request.confirmPassword()
        );

        validateNewPassword(request.newPassword(), user.getPassword());

        user.setPassword(passwordEncoder.encode(request.newPassword()));
    }

    //validar senha antiga
    public void validateOldPassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new RuntimeException("Old password is incorrect");
        }
    }

    //validar nova senha
    public void validateNewPassword(String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("New password and confirm password do not match");
        }
    }

}
