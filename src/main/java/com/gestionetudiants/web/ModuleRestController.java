package com.gestionetudiants.web;

import com.gestionetudiants.dao.ModuleRepository;
import com.gestionetudiants.entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ModuleRestController {
    @Autowired
    ModuleRepository repository;

    @GetMapping("/modules")
    public List<Module> getModules(){
        return repository.findAll();
    }
    @DeleteMapping("/deleteModule/{id}")
    @Transactional
    public Long deleteModule(@PathVariable Long id){
        try {
            return repository.deleteById(id);
            //return "Enregistrement supprimé avec sucées";
        } catch (Exception e) {
            return 0l;
        }
    }

    @PostMapping("/enregisterModule")
    public Module register(@RequestBody Module module) {
        return repository.save(module);
    }
    @PutMapping("/modules/{id}")
    @Transactional
    public Module updateModule(@PathVariable Long id,@RequestBody Module module) {
        Module modUp= repository.findOne(id);
        modUp.setNom(module.getNom());
        modUp.setCode(module.getCode());
        modUp.setHoraires(module.getHoraires());
        modUp.setEnseignant(module.getEnseignant());
        return repository.save(modUp);
    }
}
