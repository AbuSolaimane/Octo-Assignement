package ma.octo.assignement.service;

import java.util.List;

import ma.octo.assignement.entity.Compte;



public interface CompteService {
	List<Compte> getAllComptes();
	Compte getCompte(Long id);	
	Compte createCompte(Compte compte);
	Compte updateCompte(Compte compte, Long id);
	Compte deleteCompte(Long id);
}
