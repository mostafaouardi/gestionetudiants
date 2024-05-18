package com.gestionetudiants.web;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import com.gestionetudiants.dao.UserRepository;
import com.gestionetudiants.entities.AppUser;
import com.gestionetudiants.models.FormAppUser;
import com.gestionetudiants.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public  List<AppUser> getUsers(){
		List<AppUser> users =  userRepository.findAll();
		List<AppUser> userRes = new ArrayList<AppUser>();
		users.forEach(u -> {
			AppUser us = new AppUser();
			us.setId(u.getId());
			us.setUsername(u.getUsername());
			us.setRoles(u.getRoles());
			userRes.add(us);
		});
		return userRes;
	}
	@PostMapping("/users")
		public AppUser addUser(@RequestBody FormAppUser userForm) {
		AppUser user= accountService.findUserByUsername(userForm.getUsername());
		if (user != null) {
			throw new RuntimeException("user existe déja");
		};		
		AppUser userNew = accountService.saveUser(new AppUser(null,userForm.getUsername(),userForm.getPassword(),null,true,null));
		accountService.addRoleToUser(userForm.getUsername(),userForm.getRole());
		return userNew;
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public Long deleteUser(@PathVariable Long id){
		try {
			accountService.removeUser(id);
			return 1l;
		} catch (Exception e) {
			return 0l;
		}	
	}

}
