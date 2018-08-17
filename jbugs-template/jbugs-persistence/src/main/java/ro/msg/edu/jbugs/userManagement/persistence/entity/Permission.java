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
@EqualsAndHashCode(callSuper = true, exclude = "roles")
@ToString(callSuper = true, exclude = "roles")
@Builder
public class Permission extends BaseEntity<Long>{

    @Enumerated(EnumType.STRING)
    private PermissionType type;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();
}
