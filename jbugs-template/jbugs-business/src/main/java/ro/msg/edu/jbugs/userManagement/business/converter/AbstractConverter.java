package ro.msg.edu.jbugs.userManagement.business.converter;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class AbstractConverter<Entity, Dto>
        implements Converter<Entity, Dto> {

    public Collection<Dto> convertEntitiesToDtos(Collection<Entity> entities){
        return entities.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toSet());
    }

    public Collection<Entity> convertDtosToEntities(Collection<Dto> dtos) {
        return dtos.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toSet());
    }
}
