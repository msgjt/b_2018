package ro.msg.edu.jbugs.userManagement.business.converter;

import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserConverter extends AbstractConverterBaseEntityConverter<User, UserDto> {

    @Override
    public User convertDtoToEntity(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .mobileNumber(userDto.getMobileNumber())
                .build();
    }

    @Override
    public UserDto convertEntityToDto(User user) {
        UserDto userDto = UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .status(user.getStatus().toString())
                .build();
        userDto.setId(user.getId());
        return userDto;
    }
}
