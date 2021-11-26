package ma.octo.assignement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.octo.assignement.entity.Utilisateur;
import ma.octo.assignement.service.UtilisateurService;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService; 
	
	@GetMapping
	public List<Utilisateur> getAllUtilisateurs(){
		return utilisateurService.getAllUtilisateurs();
	}
	
	@GetMapping("/{id}")
	public Utilisateur getUtilisateur(@PathVariable("id") Long id){
		return utilisateurService.getUtilisateur(id);
	}
	
	@PostMapping
	public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur){
		return utilisateurService.createUtilisateur(utilisateur);
	}
	
	@PutMapping("{id}")
	public Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur,@PathVariable("id") Long id){
		return utilisateurService.updateUtilisateur(id, utilisateur);
	}
	
	@DeleteMapping("{id}")
	public Utilisateur deleteUtilisateur(@PathVariable("id") Long id){
		return utilisateurService.deleteUtilisateur(id);
	}

}
