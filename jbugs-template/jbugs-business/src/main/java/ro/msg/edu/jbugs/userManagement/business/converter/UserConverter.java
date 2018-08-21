package ro.msg.edu.jbugs.userManagement.business.converter;

import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.RoleType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequestScoped
public class UserConverter extends AbstractConverterBaseEntityConverter<User, UserDto> {

    @Inject
    private RoleConverter roleConverter;

    @Override
    public User convertDtoToEntity(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .mobileNumber(userDto.getMobileNumber())
                .build();
        if(userDto.getId() != null){
            user.setId(userDto.getId());
        }
        if(userDto.getRoles() != null && !userDto.getRoles().isEmpty()){
            Set<Role> roles =
                    new HashSet<>(roleConverter.convertDtosToEntities(userDto.getRoles()));
            user.setRoles(roles);
        }
        return user;
    }

    @Override
    public UserDto convertEntityToDto(User user) {
        UserDto userDto = UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .build();
        if(user.getStatus() != null){
            userDto.setStatus(user.getStatus().name());
        }
        userDto.setId(user.getId());
        return userDto;
    }
}
