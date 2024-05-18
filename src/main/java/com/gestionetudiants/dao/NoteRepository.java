package com.gestionetudiants.dao;

import com.gestionetudiants.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note , Long> {
    public Long deleteById(Long id);
}
