package com.tesis.teamsoft.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class RoleEntity implements Serializable {

    //Atributos
    //===================================================================================
    @Id//<--Marca el atributo como llave primaria de la entidad
    @Basic(optional = false)//<--Se utiliza para definir que un atributo es obligatorio y debe tener valor
    @NotNull//<--Se utiliza para especificar que un campo no puede ser null
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")//<--Indica que el valor de la llave primaria se genera automáticamente
    @SequenceGenerator(sequenceName = "hibernate_sequence", allocationSize = 1, name = "CUST_SEQ")//<--Se utiliza para definir un generador de secuencias
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "role_name")
    private String roleName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "role_desc")
    private String roleDesc;

    @Basic(optional = false)
    @NotNull
    @Column(name = "impact")
    private float impact;

    @NotNull
    @Column(name = "is_boss")
    private boolean isBoss;

    // Relación de roles incompatibles - roles que no pueden trabajar juntos
    @JoinTable(
            name = "incompatible_roles",
            // Columna que referencia este rol
            joinColumns = @JoinColumn(name = "role_id"),
            // Columna que referencia el rol incompatible
            inverseJoinColumns = @JoinColumn(name = "incompatible_role_id")
    )
    // Un rol puede ser incompatible con muchos otros roles
    @ManyToMany(fetch = FetchType.LAZY)
    private List<RoleEntity> incompatibleRoles;

    // Relación inversa - roles que tienen a este como incompatible
    @ManyToMany(mappedBy = "incompatibleRoles", fetch = FetchType.LAZY)
    private List<RoleEntity> incompatibleWith;

    /*Se establece la relacion con AssignedRole(tabla y clase),
    a traves del atributo mapeado(roles) en la clase AssignedRoleEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<AssignedRoleEntity> assignedRoleList;

    /*Se establece la relacion con RoleCompetition(tabla y clase),
    a traves del atributo mapeado(roles) en la clase RoleCompetitionEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", orphanRemoval = true)
    private List<RoleCompetitionEntity> roleCompetitionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<RoleExperienceEntity> roleExperienceList;

    /*Se establece la relacion con ProjectRoleIncomp(tabla y clase),
    a traves del atributo mapeado(rolesA) en la clase ProjectRoleIncompEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleA")
    private List<ProjectRoleIncompEntity> projectRoleIncompListA;

    /*Se establece la relacion con ProjectRoleIncomp(tabla y clase),
    a traves del atributo mapeado(rolesB) en la clase ProjectRoleIncompEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleB")
    private List<ProjectRoleIncompEntity> projectRoleIncompListB;

    /*Se establece la relacion con PersonalInterest(tabla y clase),
     a traves del atributo mapeado(roles) en la clase PersonalInterestEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<PersonalInterestsEntity> personalInterestsList;

    /*Se establece la relacion con RolePersonEval(tabla y clase),
     a traves del atributo mapeado(roles) en la clase RolePersonEvalEntity*/
    @OneToMany(mappedBy = "roles")
    private List<RolePersonEvalEntity> rolePersonEvalList;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
//    private List<ProjectRoles> projectRolesList;
    //===================================================================================


    //Métodos
    //===================================================================================
    @Override
    public boolean equals(Object object) {
        if(object instanceof RoleEntity other) {
            return this.id != null && other.id != null && this.id.equals(other.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    //===================================================================================
}
