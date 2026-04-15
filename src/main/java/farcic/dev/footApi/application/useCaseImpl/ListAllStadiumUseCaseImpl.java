package farcic.dev.footApi.application.useCaseImpl;

import farcic.dev.footApi.application.useCase.ListAllStadiumUseCase;
import farcic.dev.footApi.domain.gateway.StadiumGateway;
import farcic.dev.footApi.presentation.dto.StadiumRespondeDto;
import farcic.dev.footApi.presentation.mapperDto.MapperStadiumDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ListAllStadiumUseCaseImpl implements ListAllStadiumUseCase {

    private final StadiumGateway gateway;
    private final MapperStadiumDto mapperdto;

    public ListAllStadiumUseCaseImpl(StadiumGateway gateway, MapperStadiumDto mapperdto) {
        this.gateway = gateway;
        this.mapperdto = mapperdto;
    }

    @Override
    public Page<StadiumRespondeDto> execute(Pageable pageable) {


        return gateway.execute(pageable)
                .map(e -> mapperdto.toResponse(e));
    }
}
