package farcic.dev.footApi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

public record ClubDetatilsResponse(

        Long id,
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate founded,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String urlImg,
        StadiumResponseDto stadium


) {
}
