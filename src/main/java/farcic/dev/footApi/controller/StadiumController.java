package farcic.dev.footApi.controller;

import farcic.dev.footApi.dto.StadiumRequestDto;
import farcic.dev.footApi.dto.StadiumResponseDto;
import farcic.dev.footApi.service.StadiumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/stadiums")
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @GetMapping
    public Page<StadiumResponseDto> listAll(Pageable pageable) {
        return stadiumService.listAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StadiumResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stadiumService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StadiumResponseDto> create(@Valid @RequestBody StadiumRequestDto stadiumRequestDto) {
        StadiumResponseDto createdStadium = stadiumService.create(stadiumRequestDto);
        URI location = URI.create("/api/v1/stadiums/" + createdStadium.id());
        return ResponseEntity.created(location).body(createdStadium);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StadiumResponseDto> update(@PathVariable Long id,
                                                     @Valid @RequestBody StadiumRequestDto stadiumRequestDto) {
        return ResponseEntity.ok(stadiumService.update(id, stadiumRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stadiumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
