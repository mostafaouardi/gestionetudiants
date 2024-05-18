package com.gestionetudiants;
import com.gestionetudiants.entities.AppRole;
import com.gestionetudiants.entities.AppUser;
import com.gestionetudiants.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class GestionEtudiantsApplication implements CommandLineRunner {

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(GestionEtudiantsApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder getBCrypt() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		/*try {
			accountService.saveRole(new AppRole(null,"ENSEIGNANT"));
			accountService.saveRole(new AppRole(null,"ADMIN"));
			accountService.saveUser(new AppUser(null,"admin","admin@",null,true,null));
			accountService.addRoleToUser("admin","ADMIN");
		}catch (Exception ex){
			System.out.println(ex.getMessage());
		}*/
	}
}
