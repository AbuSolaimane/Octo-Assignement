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

import ma.octo.assignement.entity.Compte;
import ma.octo.assignement.service.CompteService;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {
	
	@Autowired
	private CompteService compteService;
	
	@GetMapping
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }
	
	@GetMapping("{id}")
	public Compte getCompte(@PathVariable("id") Long id) {
		
		return compteService.getCompte(id);
	}
	
	@PostMapping
	public Compte createCompte(@RequestBody Compte compte) {
		return compteService.createCompte(compte);
	}
	
	@PutMapping("{id}")
	public Compte updateCompte(@PathVariable("id") Long id, @RequestBody Compte compte) {
		return compteService.updateCompte(compte, id);
	}
	
	@DeleteMapping("{id}")
	public Compte deleteCompte(@PathVariable("id") Long id) {
		return compteService.deleteCompte(id);
	}

}
