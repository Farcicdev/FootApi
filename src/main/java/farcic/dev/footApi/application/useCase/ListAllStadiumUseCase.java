package farcic.dev.footApi.application.useCase;

import farcic.dev.footApi.presentation.dto.StadiumRespondeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListAllStadiumUseCase {

    Page<StadiumRespondeDto> execute(Pageable pageable);

}
