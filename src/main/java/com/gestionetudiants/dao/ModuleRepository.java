package com.gestionetudiants.dao;

import com.gestionetudiants.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module,Long> {
    public Long deleteById(Long id);
}
