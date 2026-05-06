package farcic.dev.footApi.controller;

import farcic.dev.footApi.dto.request.StadiumRequestDto;
import farcic.dev.footApi.dto.response.StadiumResponseDto;
import farcic.dev.footApi.service.StadiumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stadiums")
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @PreAuthorize("hasAnyAuthority('SCOPE_stadium:read', 'SCOPE_admin:all')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StadiumResponseDto> listAll(Pageable pageable) {
        return stadiumService.listAll(pageable);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_stadium:write', 'SCOPE_admin:all')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StadiumResponseDto createStadium(@Valid @RequestBody StadiumRequestDto request) {
        return stadiumService.createStadium(request);
    }
}
