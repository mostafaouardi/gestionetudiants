package com.gestionetudiants.dao;

import com.gestionetudiants.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {
    public Long deleteById(Long id);
}
