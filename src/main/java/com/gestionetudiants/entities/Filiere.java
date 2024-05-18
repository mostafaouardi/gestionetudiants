package com.gestionetudiants.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filiere {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String anneeAccr;
    private String nomCord;
    @OneToMany(mappedBy = "filiere",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Collection<Module> modules=new ArrayList<>();
}
