package farcic.dev.footApi.presentation.controller;

import farcic.dev.footApi.application.useCase.ListAllStadiumUseCase;
import farcic.dev.footApi.presentation.dto.StadiumRespondeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stadium")
@RequiredArgsConstructor
public class StadiumController {

    private final ListAllStadiumUseCase listAllStadium;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<StadiumRespondeDto> listAll(Pageable pageable) {
        return listAllStadium.execute(pageable);
    }

}
