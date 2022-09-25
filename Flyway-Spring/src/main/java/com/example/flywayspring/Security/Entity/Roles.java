package com.example.flywayspring.Security.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_title", nullable = false, unique = true)
    private RoleTitle roleTitle;

    @ManyToMany(mappedBy = "rolesCollection")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Users> usersCollection;

    public String getRoleTitle() {
        return roleTitle.toString();
    }

    public Roles setRoleTitle(String roleTitle) {
        this.roleTitle = RoleTitle.valueOf(roleTitle);
        return this;
    }
}
