package ro.msg.edu.jbugs.userManagement.business.converter;

import ro.msg.edu.jbugs.userManagement.business.dto.BaseDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.BaseEntity;

public interface ConverterBaseEntity<Entity extends BaseEntity<Long>, Dto extends BaseDto>
        extends Converter<Entity, Dto> {

}
