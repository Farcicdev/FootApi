package farcic.dev.footApi.controller;

import farcic.dev.footApi.dto.response.PossitionsResponse;
import farcic.dev.footApi.entity.PositionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {

    @GetMapping("/possition")
    @ResponseStatus(HttpStatus.OK)
    public List<PossitionsResponse> getPossitoin() {
        return Arrays.stream(PositionEnum.values())
                .map(e -> new PossitionsResponse(e.name(), e.name()))
                .toList();
    }
}
