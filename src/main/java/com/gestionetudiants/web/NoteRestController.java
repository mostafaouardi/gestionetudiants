package com.gestionetudiants.web;

import com.gestionetudiants.dao.NoteRepository;
import com.gestionetudiants.entities.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "*")
@Transactional
@RestController
public class NoteRestController {
    @Autowired
    NoteRepository repository;
    @GetMapping("/notes")
    public List<Note> getnotes(){
        return repository.findAll();
    }
    @PostMapping("/enregistrerNote")
    public Note enregisterNote(@RequestBody Note note) {
        return repository.save(note);
    }
    @DeleteMapping("/deleteNote/{id}")
    public Long deleteNote(@PathVariable Long id){
        return repository.deleteById(id);
    }
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable Long id,@RequestBody Note note) {
        Note  noteUp= repository.findOne(id);
        noteUp.setEtudiant(note.getEtudiant());
        noteUp.setModule(note.getModule());
        noteUp.setNote(note.getNote());
        return repository.save(noteUp);
    }
}
