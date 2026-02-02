package com.tesis.teamsoft.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assigned_role")
public class AssignedRoleEntity implements Serializable {

    //Atributos
    //=====================================================================================================================
    @Id//<--Marca el atributo como llave primaria de la entidad
    @Basic(optional = false)//<--Se utiliza para definir que un atributo es obligatorio y debe tener valor
    @NotNull//<--Se utiliza para especificar que un campo no puede ser null
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")//<--Indica que el valor de la llave primaria se genera automáticamente
    @SequenceGenerator(sequenceName = "hibernate_sequence", allocationSize = 1, name = "CUST_SEQ")//<--Se utiliza para definir un generador de secuencias
    private Long id;

    @Basic(optional = false)
    @NotBlank()//<--La cadena de caracteres no puede ser "", " ", o null
    private String status;

    @Basic(optional = false)
    @NotBlank()//<--La cadena de caracteres no puede ser "", " ", o null
    private String observation;

    @Column(name = "end_date")//<--Le asigna el nombre que tendra la columba en la base de datos
    @Temporal(TemporalType.DATE)//<--Indica que la variable sera de tipo fecha
    @Future//<--Establece la fecha de terminación como futura
    private Date endDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "begin_date")
    @Temporal(TemporalType.DATE)
    //@PastOrPresent//<--Establece que la fecha de inicio debe ser hoy o pasada
    private Date beginDate;

    @JoinColumn(name = "cycles_fk", referencedColumnName = "id")//<--Establece la relacion con la clase CycleEnitty
    @ManyToOne(optional = false)
    private CycleEntity cycles;

    @JoinColumn(name = "roles_fk", referencedColumnName = "id")//<--Establece la relacion con la clase RoleEnitty
    @ManyToOne(optional = false)
    private RoleEntity role;

    @JoinColumn(name = "person_fk", referencedColumnName = "id")//<--Establece la relacion con la clase PersonEnitty
    @ManyToOne(optional = false)
    private PersonEntity person;
    //=====================================================================================================================
    

    //Métodos
    //=====================================================================================================================
    @Override
    public boolean equals(Object object) {
        if(object instanceof AssignedRoleEntity other) {
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
    //=====================================================================================================================
}
