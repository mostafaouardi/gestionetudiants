package com.gestionetudiants.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Module {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String code;
    private String horaires;
    @ManyToOne()
    @JoinColumn(name="enseignant_module")
    private Enseignant enseignant;
    @ManyToOne()
    @JoinColumn(name="filiere_module")
    private Filiere filiere;
}
