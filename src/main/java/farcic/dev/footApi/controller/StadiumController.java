package farcic.dev.footApi.controller;

import farcic.dev.footApi.dto.StadiumRequestDto;
import farcic.dev.footApi.dto.StadiumResponseDto;
import farcic.dev.footApi.service.StadiumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stadium")
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
    public StadiumResponseDto create(@Valid @RequestBody StadiumRequestDto stadiumRequestDto) {
        return stadiumService.create(stadiumRequestDto);
    }
}
