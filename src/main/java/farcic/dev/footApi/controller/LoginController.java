package farcic.dev.footApi.controller;

import farcic.dev.footApi.dto.request.LoginRequestDto;
import farcic.dev.footApi.dto.response.LoginResponseDto;
import farcic.dev.footApi.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto requestDto){
        return service.login(requestDto);
    }

}
