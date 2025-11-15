package com.tesis.teamsoft.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person_conflict")
public class PersonConflictEntity implements Serializable {

    //Atributos
    //===================================================================================
    @Id//<--Marca el atributo como llave primaria de la entidad
    @Basic(optional = false)//<--Se utiliza para definir que un atributo es obligatorio y debe tener valor
    @NotNull//<--Se utiliza para especificar que un campo no puede ser null
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")//<--Indica que el valor de la llave primaria se genera automáticamente
    @SequenceGenerator(sequenceName = "hibernate_sequence", allocationSize = 1, name = "CUST_SEQ")//<--Se utiliza para definir un generador de secuencias
    private Long id;

    @JoinColumn(name = "index_fk", referencedColumnName = "id")//<--Establece la relacion con la clase ConflictIndexEnitty
    @ManyToOne(optional = false)
    private ConflictIndexEntity index;

    @JoinColumn(name = "person_conflict_fk", referencedColumnName = "id")//<--Establece la relacion con la clase PersonEnitty
    @ManyToOne(optional = false)
    private PersonEntity personConflict;

    @JoinColumn(name = "person_fk", referencedColumnName = "id")//<--Establece la relacion con la clase PersonEnitty
    @ManyToOne(optional = false)
    private PersonEntity person;
    //===================================================================================


    //Métodos
    //===================================================================================
    @Override
    public boolean equals(Object object) {
        if(object instanceof PersonConflictEntity other) {
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
