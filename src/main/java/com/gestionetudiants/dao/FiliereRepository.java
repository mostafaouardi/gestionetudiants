package com.gestionetudiants.dao;

import com.gestionetudiants.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiliereRepository extends JpaRepository<Filiere,Long> {
    public Long deleteById(Long id);
}
