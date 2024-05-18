package com.gestionetudiants.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Etudiant etudiant;
    @OneToOne
    private  Module module;
    private long note;
}
