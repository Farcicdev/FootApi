package farcic.dev.footApi.controller;

import farcic.dev.footApi.dto.request.ClubRequestDto;
import farcic.dev.footApi.dto.response.ClubDetatilsResponse;
import farcic.dev.footApi.dto.response.ClubResponseDto;
import farcic.dev.footApi.dto.response.PlayerResponseDetails;
import farcic.dev.footApi.dto.response.PlayerResponseDto;
import farcic.dev.footApi.service.ClubService;
import farcic.dev.footApi.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService service;
    private final PlayerService playerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClubResponseDto> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClubDetatilsResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClubDetatilsResponse createClub(@Valid @RequestBody ClubRequestDto request) {
        return service.save(request);
    }

    @GetMapping("/{id}/players")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerResponseDto> findPlayersByClubId(@PathVariable Long id) {
        return playerService.findByClubId(id);
    }

}

