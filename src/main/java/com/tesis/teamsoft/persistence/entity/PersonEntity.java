package com.tesis.teamsoft.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class PersonEntity implements Serializable {

    @Id//<--Marca el atributo como llave primaria de la entidad
    @Basic(optional = false)//<--Se utiliza para definir que un atributo es obligatorio y debe tener valor
    @NotNull//<--Se utiliza para especificar que un campo no puede ser null
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")//<--Indica que el valor de la llave primaria se genera automáticamente
    @SequenceGenerator(sequenceName = "hibernate_sequence", allocationSize = 1, name = "CUST_SEQ")//<--Se utiliza para definir un generador de secuencias
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)//<--Restringe el tamaño del elemento, dandole mínimo y máximo
    @Column(name = "person_name")
    private String personName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "id_card")
    private String card;

    @Basic(optional = false)
    @NotNull
    @Size(max = 1024)
    @Column(name = "sur_name")
    private String surName;

    @Basic(optional = false)
    @NotNull
    @Size(max = 1024)
    private String address;

    @Basic(optional = false)
    @NotNull
    @Size(max = 1024)
    private String phone;

    @Basic(optional = false)
    @NotNull
    private Character sex;

    @Basic(optional = false)
    @NotNull
    @Size(max = 1024)
    private String email;

    @Basic(optional = false)
    @NotNull
    @Column(name = "in_date")
    @Temporal(TemporalType.DATE)//<--Indica que la variable sera de tipo fecha
    private Date inDate;

    @Basic(optional = false)
    @NotNull
    private Float workload;

    @Basic(optional = false)
    @NotNull
    private Integer experience;

    @Basic(optional = false)
    @NotNull
    @Size(max = 1024)
    private String status;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    //Relaciones OneToMany

    /*Se establece la relacion con AssignedRole(tabla y clase), a traves del atributo mapeado(person) en la clase PersonEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<AssignedRoleEntity> assignedRoleList;

    /*Se establece la relacion con CompetenceValue(tabla y clase),
     a traves del atributo mapeado(person) en la clase CompetenceValueEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<CompetenceValueEntity> competenceValueList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private  List<PersonConflictEntity> personConflictList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personConflict")
    private  List<PersonConflictEntity> personConflictWithList;

    /*Se establece la relacion con PersonalInterests(tabla y clase),
     a traves del atributo mapeado(person) en la clase PersonalInterestsEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<PersonalInterestsEntity> personalInterestsList;

    /*Se establece la relacion con PersonalProjectInterests(tabla y clase),
     a traves del atributo mapeado(person) en la clase PersonalProjectInterestsEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<PersonalProjectInterestsEntity> personalProjectInterestsList;

    /*Se establece la relacion con RolePersonEval(tabla y clase),
     a traves del atributo mapeado(person) en la clase RolePersonEvalEntity*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<RolePersonEvalEntity> roleEvaluationList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<RoleExperienceEntity> roleExperienceList;

    /*Se establece la relacion con PersonalProjectInterests(tabla y clase),
     a traves del atributo mapeado(person) en la clase PersonalProjectInterestsEntity*/
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private PersonTestEntity personTest;


    //Relaciones ManyToOne a traves de Join
    @JoinColumn(name = "county_fk", referencedColumnName = "id")//<--Establece la relacion con la clase CountyEnitty
    @ManyToOne(optional = false)
    private CountyEntity county;

    @JoinColumn(name = "race_fk", referencedColumnName = "id")//<--Establece la relacion con la clase RaceEnitty
    @ManyToOne(optional = false)
    private RaceEntity race;

    @JoinColumn(name = "group_fk", referencedColumnName = "id")//<--Indica por que atributo se unira a la entidad
    @ManyToOne(optional = false)//<--Define una relación de muchos a uno con una entidad
    private PersonGroupEntity group;

    @JoinColumn(name = "nacionality_fk", referencedColumnName = "id")//<--Establece la relacion con la clase NacionalityEnitty
    @ManyToOne(optional = false)
    private NacionalityEntity nacionality;

    @JoinColumn(name = "religion_fk", referencedColumnName = "id")//<--Establece la relacion con la clase ReligionEnitty
    @ManyToOne(optional = false)
    private ReligionEntity religion;

    @JoinColumn(name = "age_group_fk", referencedColumnName = "id")//<--Establece la relacion con la clase AgeGroupEnitty
    @ManyToOne(optional = false)
    private AgeGroupEntity ageGroup;
    //===================================================================================


    //Métodos
    //===================================================================================
    public String getFullName() {
        String result = "";
        result += personName;
        result += " " + surName;
        return result.trim();
    }

    //Método para calcular la edad de una persona
    public int getAge(){
        int age = 0;

        if(birthDate != null){    //Se comprueba que las fechas no están null
            Date today = new Date();
            LocalDate firstDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate secondDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            age = Period.between(firstDate, secondDate).getYears();
        }

        return age;
    }

    public RoleExperienceEntity getRoleExperience(Long idRol) {
        RoleExperienceEntity xp = new RoleExperienceEntity();
        for (RoleExperienceEntity roleExperience : this.roleExperienceList) {
            if ((roleExperience.getRole().getId()).equals(idRol)) {
                xp = roleExperience;
                break;
            }
        }
        return xp;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof PersonEntity other) {
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
