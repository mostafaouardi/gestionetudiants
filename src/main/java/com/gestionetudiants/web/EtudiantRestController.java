package com.gestionetudiants.web;

import com.gestionetudiants.dao.EtudiantRepository;
import com.gestionetudiants.entities.Etudiant;
import com.gestionetudiants.entities.Filiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "*")
@Transactional
@RestController
public class EtudiantRestController {
    @Autowired
    EtudiantRepository repository;
    @GetMapping("/etudiants")
    public List<Etudiant> getEtuds(){
        return repository.findAll();
    }
    @PostMapping("/enregistrerEtudiant")
    public Etudiant enregisterEtud(@RequestBody Etudiant etudiant) {
        return repository.save(etudiant);
    }
    @DeleteMapping("/deleteEtudiant/{id}")
    public Long deleteEtud(@PathVariable Long id){
        return repository.deleteById(id);
    }
    @PutMapping("/etudiants/{id}")
    public Etudiant updateEtudiant(@PathVariable Long id,@RequestBody Etudiant etud) {
        Etudiant  fUp= repository.findOne(id);
        fUp.setNom(etud.getNom());
        fUp.setPrenom(etud.getPrenom());
        fUp.setDatenaissance(etud.getDatenaissance());
        fUp.setAdresse(etud.getAdresse());
        fUp.setTelephone(etud.getTelephone());
        return repository.save(fUp);
    }
}
