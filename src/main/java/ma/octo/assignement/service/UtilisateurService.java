package ma.octo.assignement.service;

import java.util.List;

import ma.octo.assignement.entity.Utilisateur;

public interface UtilisateurService {
	List<Utilisateur> getAllUtilisateurs();
	Utilisateur getUtilisateur(Long id);
	Utilisateur createUtilisateur(Utilisateur utilisateur);
	Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur);
	Utilisateur deleteUtilisateur(Long id);
}
