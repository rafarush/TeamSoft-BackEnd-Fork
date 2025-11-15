package com.tesis.teamsoft.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "person_test")
public class PersonTestEntity implements Serializable {

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
    @Column(name = "e_s")//<--Le asigna el nombre que tendra la columba en la base de datos
    private Character e_S;

    @Basic(optional = false)
    @NotNull
    @Column(name = "i_d")
    private Character i_D;

    @Basic(optional = false)
    @NotNull
    @Column(name = "c_o")
    private Character c_O;

    @Basic(optional = false)
    @NotNull
    @Column(name = "i_s")
    private Character i_S;

    @Basic(optional = false)
    @NotNull
    @Column(name = "c_e")
    private Character c_E;

    @Basic(optional = false)
    @NotNull
    @Column(name = "i_r")
    private Character i_R;

    @Basic(optional = false)
    @NotNull
    @Column(name = "m_e")
    private Character m_E;

    @Basic(optional = false)
    @NotNull
    @Column(name = "c_h")
    private Character c_H;

    @Basic(optional = false)
    @NotNull
    @Column(name = "i_f")
    private Character i_F;

    @Basic(optional = true)
    @Size(min = 1, max = 1024)//<--Restringe el tamaño del elemento, dandole mínimo y máximo
    @Column(name = "tipo_m_b")
    private String tipoMB;

    @JoinColumn(name = "person_fk", referencedColumnName = "id")//<--Establece la relacion con la clase PersonEnitty
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private PersonEntity person;
    //===================================================================================


    //Métodos
    //===================================================================================
    @Override
    public boolean equals(Object object) {
        if(object instanceof PersonTestEntity other) {
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
