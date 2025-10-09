package com.tesis.teamsoft.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class PersonEntity {

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
    private String idCard;

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

    @JoinColumn(name = "religion_fk", referencedColumnName = "id")//<--Establece la relacion con la tabla Religion
    @ManyToOne(optional = false)
    private ReligionEntity religion;

    /**TODO
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workersFk")//<--Define una relación de uno a muchos entre entidades, indica ademas por que columna se unira a otras entidades
    private List<AssignedRole> assignedRoleList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workerFk")
    private List<RoleExperience> roleExperienceList;


    @JoinColumn(name = "county_fk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private County countyFk;


    @JoinColumn(name = "group_fk", referencedColumnName = "id")//<--Indica por que atributo se unira a la entidad
    @ManyToOne(optional = false)//<--Define una relación de muchos a uno con una entidad
    private PersonGroup groupFk;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workersFk")
    private List<PersonalInterests> personalInterestsList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workersFk")
    private List<PersonalProjectInterests> personalProjectInterestsList;


    @OneToMany(mappedBy = "workerFk")
    private List<RoleEvaluation> roleEvaluationList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workersFk")
    private List<CompetenceValue> competenceValueList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workerConflictFk")
    private List<WorkerConflict> workerConflictList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workerFk")
    private List<WorkerConflict> workerConflictList1;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "workerFk")
    private WorkerTest workerTest;


    @OneToMany(mappedBy = "workerFk")
    private List<Users> usersList;


    @JoinColumn(name = "race_fk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Race racefk;


    //@ManyToMany <--Indica una relación de muchos a muchos con otra entidad

    @JoinColumn(name = "nacionality_fk", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Nacionality nacionalityfk;**/


}
