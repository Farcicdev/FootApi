package farcic.dev.footApi.controller;

import farcic.dev.footApi.dto.request.UpdatePasswordRequest;
import farcic.dev.footApi.dto.request.UsersRequestDto;
import farcic.dev.footApi.dto.response.UsersResponseDto;
import farcic.dev.footApi.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponseDto create(@Valid @RequestBody UsersRequestDto requestDto) {
        return service.create(requestDto);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePassword(@RequestParam Long id, @Valid @RequestBody UpdatePasswordRequest request) {
        service.updatePassword(id, request);
    }

}
