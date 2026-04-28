package farcic.dev.footApi.controller;

import farcic.dev.footApi.dto.request.StadiumRequestDto;
import farcic.dev.footApi.dto.response.StadiumResponseDto;
import farcic.dev.footApi.service.StadiumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stadiums")
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StadiumResponseDto> listAll(Pageable pageable) {
        return stadiumService.listAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StadiumResponseDto createStadium(@Valid @RequestBody StadiumRequestDto request) {
        return stadiumService.createStadium(request);
    }
}
