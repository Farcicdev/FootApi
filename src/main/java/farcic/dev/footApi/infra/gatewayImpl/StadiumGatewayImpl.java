package farcic.dev.footApi.infra.gatewayImpl;

import farcic.dev.footApi.domain.core.Stadium;
import farcic.dev.footApi.domain.gateway.StadiumGateway;
import farcic.dev.footApi.infra.mapper.StadiumMapper;
import farcic.dev.footApi.infra.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StadiumGatewayImpl implements StadiumGateway {

    private final StadiumRepository repository;
    private final StadiumMapper mapper;


    @Override
    public Page<Stadium> execute(Pageable pageable) {
        return repository.findAll(pageable)
                .map(entity -> mapper.toDomain(entity));
    }
}
