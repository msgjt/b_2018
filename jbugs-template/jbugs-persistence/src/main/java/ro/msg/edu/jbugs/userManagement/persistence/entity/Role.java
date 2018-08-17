package ro.msg.edu.jbugs.userManagement.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"users","permissions"})
@ToString(callSuper = true, exclude = {"users","permissions"})
@Builder
public class Role extends BaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    private RoleType type;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Permission> permissions = new HashSet<>();


}
