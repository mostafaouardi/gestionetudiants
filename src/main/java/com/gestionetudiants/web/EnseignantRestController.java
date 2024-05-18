package com.gestionetudiants.web;

import com.gestionetudiants.dao.EnseignantRepository;
import com.gestionetudiants.dao.UserRepository;
import com.gestionetudiants.entities.AppUser;
import com.gestionetudiants.entities.Enseignant;
import com.gestionetudiants.models.FormEnseignantUser;
import com.gestionetudiants.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "*")
@Transactional
@RestController
public class EnseignantRestController {

    @Autowired
    EnseignantRepository repository;
    @Autowired
    AccountService accountService;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/enseignants")
    public List<Enseignant> getEnseignants(){
        return repository.findAll();
    }
    @PostMapping("/enregisterEnseignantUser")
    public Enseignant register(@RequestBody FormEnseignantUser userForm) {
        //if(!userForm.getPassword().equals(userForm.getRepassword()))
          //  throw new RuntimeException("les passswords ne sont pas conformes");
        //AppUser user= accountService.findUserByUsername(userForm.getUsername());
        //if (user != null) {
          //  if(user.getActive().equals(true)) throw new RuntimeException("user active déja");
          //  else {throw new RuntimeException("user existe déja");}
        //};
        Enseignant ens = repository.save(new Enseignant(null, userForm.getNom()
                , userForm.getPrenom(),  userForm.getVille(), userForm.getTelephone(),  userForm.getUsername(),null));
        if (ens == null) throw new RuntimeException("Erreur en ajoutant l'enseignant");
        accountService.saveUser(new AppUser(null,userForm.getUsername(),userForm.getPassword(),null,true,null));
        accountService.addRoleToUser(userForm.getUsername(),"ENSEIGNANT");
        return ens;
    }

    @DeleteMapping("/deleteEns/{id}")
    public Long deleteEns(@PathVariable Long id){
        try {
            Enseignant ens = repository.findOne(id);
            AppUser appuser =  accountService.findUserByUsername(ens.getUsername());
            Long res = repository.deleteById(id);
            accountService.removeUser(appuser.getId());
            return res;
            //return "Enregistrement supprimé avec sucées";
        } catch (Exception e) {
            return 0l;
        }
    }
    @PutMapping("/enseignants/{id}")
    public Enseignant updateEnseignant(@PathVariable Long id,@RequestBody Enseignant ens) {
        Enseignant ensUp= repository.findOne(id);
        ensUp.setNom(ens.getNom());
        ensUp.setPrenom(ens.getPrenom());
        ensUp.setTelephone(ens.getTelephone());
        ensUp.setVille(ens.getVille());
        return repository.save(ensUp);
    }

    @PutMapping("/updateEnseignantUser/{id}")
    public Enseignant updateVend(@RequestBody FormEnseignantUser userForm, @PathVariable Long id) {
        if(!userForm.getPassword().equals(userForm.getRepassword()))
            throw new RuntimeException("les passswords ne sont pas conformes");
        // update vendeur infos but username
        Enseignant ensUp= repository.findOne(id);
        ensUp.setNom(userForm.getNom());
        ensUp.setPrenom(userForm.getPrenom());
        ensUp.setTelephone(userForm.getTelephone());
        ensUp.setVille(userForm.getVille());
        //remove old user
        AppUser oldUser = userRepository.findByUsername(ensUp.getUsername());
        accountService.removeUser(oldUser.getId());
        //create new user
        accountService.saveUser(new AppUser(null,userForm.getUsername(),userForm.getPassword(),null,true,null));
        //Add roles to the new appuser
        accountService.addRoleToUser(userForm.getUsername(),"ENSEIGNANT");
        //replace the new Username value in the updated Vendeur and return the response
        ensUp.setUsername(userForm.getUsername());
        return repository.save(ensUp);
    }
}
