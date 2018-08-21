package ro.msg.edu.jbugs.userManagement.persistence.entity;

import lombok.*;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.UserStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"roles", "notifications", "bugsCreated", "bugsAssigned"})
@ToString(callSuper = true, exclude = {"roles", "notifications", "bugsCreated", "bugsAssigned"})
@Builder
@NamedQueries({
        @NamedQuery(name = User.GET_ALL_USERS, query = "select distinct u from User u"),
        @NamedQuery(name = User.GET_USER_BY_USERNAME, query = "select distinct u from User u where u.username=:username"),
        @NamedQuery(name = User.GET_USER_BY_EMAIL, query = "select distinct u from User u where u.email=:email"),
        @NamedQuery(name = User.GET_USER_BY_USERNAME_WITH_ROLES_AND_PERMISSIONS,
        query = "select distinct u from User u left join fetch u.roles r left join fetch r.permissions where u.username=:username"),
        @NamedQuery(name = User.UPDATE_USER_STATUS_BY_USERNAME, query = "update User u set u.status=:status where u.username=:username")
})
public class User extends BaseEntity<Long> {

    //named queries
    public static final String GET_ALL_USERS = "getAllUsers";
    public static final String GET_USER_BY_USERNAME = "getUserByUsername";
    public static final String GET_USER_BY_EMAIL = "getUserByEmail";
    public static final String GET_USER_BY_USERNAME_WITH_ROLES_AND_PERMISSIONS = "getUserByUsernameWithRolesAndPermissions";
    public static final String UPDATE_USER_STATUS_BY_USERNAME = "updateUserStatusByUsername";

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mobileNumber;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    private Set<Bug> bugsCreated = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignee")
    private Set<Bug> bugsAssigned = new HashSet<>();

}
