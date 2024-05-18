package com.gestionetudiants.dao;

import com.gestionetudiants.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    public Long deleteById(Long id);
}
