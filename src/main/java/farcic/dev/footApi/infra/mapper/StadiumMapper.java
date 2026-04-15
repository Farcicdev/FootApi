package farcic.dev.footApi.infra.mapper;

import farcic.dev.footApi.domain.core.Stadium;
import farcic.dev.footApi.infra.entity.StadiumEntity;
import org.springframework.stereotype.Component;

@Component
public class StadiumMapper {

    public Stadium toDomain(StadiumEntity entity) {
        return new Stadium(
                entity.getId(),
                entity.getName(),
                entity.getCity(),
                entity.getCapacity(),
                entity.getUrlImg()
        );
    }

    public StadiumEntity toEntity(Stadium core) {
        return StadiumEntity.builder()
                .id(core.id())
                .name(core.name())
                .city(core.city())
                .capacity(core.capacity())
                .urlImg(core.urlImg())
                .build();
    }

}
