package ma.octo.assignement.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.entity.Compte;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.service.CompteService;

@Service
@Transactional
public class CompteServiceImpl implements CompteService{
	
	@Autowired
	private CompteRepository compteRepository;
	
	@Override
	public List<Compte> getAllComptes() {
		return compteRepository.findAll();
	}

	@Override
	public Compte getCompte(Long id) {
		return compteRepository.findById(id).get();
	}

	@Override
	public Compte createCompte(Compte compte) {
		return compteRepository.save(compte);
	}

	@Override
	public Compte updateCompte(Compte compte, Long id) {
		Compte oldCompte = compteRepository.findById(id).get();
		oldCompte.setNrCompte(compte.getNrCompte());
		oldCompte.setRib(compte.getRib());
		oldCompte.setSolde(compte.getSolde());
		oldCompte.setUtilisateur(compte.getUtilisateur());
		return compteRepository.save(oldCompte);
	}

	@Override
	public Compte deleteCompte(Long id) {
		Compte compte = compteRepository.findById(id).get();
		compteRepository.delete(compte);
		return compte;
	}

}
