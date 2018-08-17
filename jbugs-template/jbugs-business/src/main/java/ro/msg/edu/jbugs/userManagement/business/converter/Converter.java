package ro.msg.edu.jbugs.userManagement.business.converter;

public interface Converter<Entity, Dto> {
    Entity convertDtoToEntity(Dto dto);

    Dto convertEntityToDto(Entity entity);
}
