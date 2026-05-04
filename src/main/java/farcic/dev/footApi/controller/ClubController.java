package farcic.dev.footApi.controller;

import farcic.dev.footApi.dto.request.ClubRequestDto;
import farcic.dev.footApi.dto.response.ClubDetatilsResponse;
import farcic.dev.footApi.dto.response.ClubResponseDto;
import farcic.dev.footApi.service.ClubService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClubResponseDto> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ClubDetatilsResponse findById(Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClubDetatilsResponse createClub(@Valid @RequestBody ClubRequestDto request) {
        return service.save(request);
    }

}

