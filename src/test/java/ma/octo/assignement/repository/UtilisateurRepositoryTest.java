package ma.octo.assignement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ma.octo.assignement.entity.Utilisateur;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UtilisateurRepositoryTest {
	
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Test
	public void getUtilisateur() {
		
		Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastname("last1");
		utilisateur1.setFirstname("first1");
		utilisateur1.setGender("Male");
		utilisateur1.setBirthdate(new Date());
		
		utilisateurRepository.save(utilisateur1);
				
		assertEquals("first1", utilisateurRepository.findAll().get(0).getFirstname());
	}

	@Test
	public void getAllUtilisateurs() {
		
		
		Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastname("last1");
		utilisateur1.setFirstname("first1");
		utilisateur1.setGender("Male");
		utilisateur1.setBirthdate(new Date());
		
		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastname("last2");
		utilisateur2.setFirstname("first2");
		utilisateur2.setGender("Male");
		utilisateur2.setBirthdate(new Date());

		utilisateurRepository.save(utilisateur1);
		utilisateurRepository.save(utilisateur2);
		
		assertEquals(2, utilisateurRepository.findAll().size());
		
	}
	
	@Test
	public void createUtilisateur() {
		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastname("last2");
		utilisateur2.setFirstname("first2");
		utilisateur2.setGender("Male");
		utilisateur2.setBirthdate(new Date());
		
		assertNotNull(utilisateurRepository.save(utilisateur2));
		
		
	}
	
	
	
	
}
