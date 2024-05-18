package com.gestionetudiants.web;

import com.gestionetudiants.dao.FiliereRepository;
import com.gestionetudiants.entities.Filiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "*")
@Transactional
@RestController
public class FiliereRestController {
    @Autowired
    FiliereRepository repository;
    @GetMapping("/filieres")
    public List<Filiere> getFilieres(){
        return repository.findAll();
    }
    @PostMapping("/enregistrerFiliere")
    public Filiere register(@RequestBody Filiere filiere) {
        return repository.save(filiere);
    }
    @DeleteMapping("/deleteFiliere/{id}")
    public Long deleteFil(@PathVariable Long id){
            return repository.deleteById(id);
    }
    @PutMapping("/filieres/{id}")
    public Filiere updateFiliere(@PathVariable Long id,@RequestBody Filiere fil) {
        Filiere  fUp= repository.findOne(id);
        fUp.setNom(fil.getNom());
        fUp.setAnneeAccr(fil.getAnneeAccr());
        fUp.setNomCord(fil.getNomCord());
        return repository.save(fUp);
    }
}
