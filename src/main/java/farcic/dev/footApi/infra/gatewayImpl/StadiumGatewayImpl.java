package farcic.dev.footApi.infra.gatewayImpl;

import farcic.dev.footApi.domain.gateway.StadiumGateway;
import farcic.dev.footApi.infra.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StadiumGatewayImpl implements StadiumGateway {

    private final StadiumRepository repository;

}
