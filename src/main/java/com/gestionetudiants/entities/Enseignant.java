package com.gestionetudiants.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Enseignant {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    private String ville;
    private String telephone;
    private String username;

    @OneToMany(mappedBy = "enseignant",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
	private Collection<Module> modules=new ArrayList<>();
}
