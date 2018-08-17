package ro.msg.edu.jbugs.userManagement.business.converter;

import ro.msg.edu.jbugs.userManagement.business.dto.BaseDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.BaseEntity;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractConverterBaseEntityConverter<Entity extends BaseEntity<Long>, Dto extends BaseDto>
        extends AbstractConverter<Entity, Dto> implements ConverterBaseEntity<Entity, Dto> {

    public Set<Long> convertEntitiesToIDs(Set<Entity> entities) {
        return entities.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Long> convertDTOsToIDs(Set<Dto> dtos) {
        return dtos.stream()
                .map(BaseDto::getId)
                .collect(Collectors.toSet());
    }

}
