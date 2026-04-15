package farcic.dev.footApi.domain.gateway;

import farcic.dev.footApi.domain.core.Stadium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StadiumGateway {

    Page<Stadium> execute(Pageable pageable);

}
