package com.tesis.teamsoft.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "age_group")
public class AgeGroupEntity implements Serializable {

    //Atributos
    //=====================================================================================================================
    @Id//<--Marca el atributo como llave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")//<--Indica que el valor de la llave primaria se genera automáticamente
    @SequenceGenerator(sequenceName = "hibernate_sequence", allocationSize = 1, name = "CUST_SEQ")//<--Se utiliza para definir un generador de secuencias
    private Long id;

    /*Nombre del grupo de edad, Se establece como NotNull, tamaño mínimo 1 y máximo 255*/
    @Basic(optional = false)
    @NotBlank(message = "Age group name is required")//<--La cadena de caracteres no puede ser "", " ", o null
    @Column(name = "age_group_name", unique = true  )//<--Le asigna el nombre que tendra la columba en la base de datos
    private String ageGroupName;//<--Establece que el atributo sera único

    @Basic(optional = false)
    @NotNull(message = "Maximum age is required")
    @Min(value = 0, message = "Maximum age must be at least 0")//Establece límites para la edad máxima o mínima
    @Max(value = 150, message = "Maximum age cannot exceed 150")
    @Column(name = "max_age")
    private int maxAge;

    @Basic(optional = false)
    @NotNull(message = "Minimum age is required")
    @Min(value = 0, message = "Minimum age must be at least 0")//Establece límites para la edad máxima o mínima
    @Max(value = 150, message = "Minimum age cannot exceed 150")
    @Column(name = "min_age")
    private int minAge;

    /*Se establece la relacion con Person(tabla y clase),
     a traves del atributo mapeado(ageGroup) en la clase PersonEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ageGroup")
    private List<PersonEntity> personList;
    //=====================================================================================================================


    //Métodos
    //=====================================================================================================================
    @Override
    public boolean equals(Object object) {
        if(object instanceof AgeGroupEntity other) {
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
