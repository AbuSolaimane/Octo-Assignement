package ma.octo.assignement.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.entity.Utilisateur;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.service.UtilisateurService;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository; 
	
	@Override
	public List<Utilisateur> getAllUtilisateurs() {
		return utilisateurRepository.findAll();
	}

	@Override
	public Utilisateur getUtilisateur(Long id) {
		return utilisateurRepository.findById(id).get();
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
		Utilisateur oldUtilisateur = utilisateurRepository.findById(id).get();
		oldUtilisateur.setBirthdate(utilisateur.getBirthdate());
		oldUtilisateur.setFirstname(utilisateur.getFirstname());
		oldUtilisateur.setGender(utilisateur.getGender());
		oldUtilisateur.setLastname(utilisateur.getLastname());
		oldUtilisateur.setUsername(utilisateur.getUsername());
		return utilisateurRepository.save(oldUtilisateur);
	}

	@Override
	public Utilisateur deleteUtilisateur(Long id) {
		
		Utilisateur utilisateur = utilisateurRepository.findById(id).get();
		utilisateurRepository.deleteById(id);
		return utilisateur;
	}

}
