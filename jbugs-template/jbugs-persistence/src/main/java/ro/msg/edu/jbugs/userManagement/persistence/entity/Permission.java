package ro.msg.edu.jbugs.userManagement.persistence.entity;

import lombok.*;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.PermissionType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "roles")
@ToString(exclude = "roles")
@Builder

@NamedQueries({
        @NamedQuery(name = Permission.GET_ALL_PERMISSIONS, query = "select distinct p from Permission p")
})
public class Permission extends BaseEntity<Long>{

    public static final String GET_ALL_PERMISSIONS = "GET_ALL_PERMISSIONS";


    @Enumerated(EnumType.STRING)
    private PermissionType type;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();
}
