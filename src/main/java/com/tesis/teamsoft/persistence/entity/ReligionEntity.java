package com.tesis.teamsoft.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "religion")
public class ReligionEntity implements Serializable {

    //Atributos
    //===================================================================================
    @Id//<--Marca el atributo como llave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//<--Indica que el valor de la llave primaria se genera automáticamente
    @SequenceGenerator(sequenceName = "hibernate_sequence", allocationSize = 1, name = "CUST_SEQ")//<--Se utiliza para definir un generador de secuencias
    private Long id;

    @Basic(optional = false)
    @NotBlank()//<--La cadena de caracteres no puede ser "", " ", o null
    @Column(name = "religion_name", unique = false)//<--Le asigna el nombre que tendra la columba en la base de datos
    private String religionName;

    /*Se establece la relacion con Person(tabla y clase), a traves del atributo mapeado(religion) en la clase PersonEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "religion")
    private List<PersonEntity> personList;
    //===================================================================================


    //Métodos
    //===================================================================================
    @Override
    public boolean equals(Object object) {
        if(object instanceof ReligionEntity other) {
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
