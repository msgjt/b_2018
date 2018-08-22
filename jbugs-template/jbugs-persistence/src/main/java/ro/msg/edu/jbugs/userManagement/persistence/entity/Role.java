package ro.msg.edu.jbugs.userManagement.persistence.entity;

import lombok.*;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.RoleType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"users","permissions"})
@ToString(exclude = {"users","permissions"})
@Builder

@NamedQueries({
        @NamedQuery(name = Role.GET_BY_TYPES, query="select distinct r from Role r where r.type in :types"),
        @NamedQuery(name = Role.GET_ALL_ROLES, query="select distinct r from Role r")
})

public class Role extends BaseEntity<Long> {


    public static final String GET_BY_TYPES = "GET_BY_TYPES";
    public static final String GET_ALL_ROLES = "GET_ALL_ROLES";


    @Enumerated(EnumType.STRING)
    private RoleType type;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Permission> permissions = new HashSet<>();


}
