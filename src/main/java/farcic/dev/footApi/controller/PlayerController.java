package farcic.dev.footApi.controller;

import farcic.dev.footApi.config.annotations.CanReadPlayer;
import farcic.dev.footApi.config.annotations.CanWritePlayer;
import farcic.dev.footApi.dto.request.PlayerRequestDto;
import farcic.dev.footApi.dto.response.PlayerResponseDetails;
import farcic.dev.footApi.dto.response.PlayerResponseDto;
import farcic.dev.footApi.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @CanReadPlayer
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PlayerResponseDto> findAll(Pageable pageable) {
        return playerService.findall(pageable);
    }

    @CanReadPlayer
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerResponseDetails findById(Long id) {
        return playerService.findById(id);
    }

    @CanWritePlayer
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerResponseDetails save(@Valid @RequestBody PlayerRequestDto request) {
        return playerService.save(request);
    }

}
