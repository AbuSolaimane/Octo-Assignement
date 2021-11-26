package ma.octo.assignement.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.entity.Audit;
import ma.octo.assignement.entity.Compte;
import ma.octo.assignement.entity.Versement;
import ma.octo.assignement.entity.util.EventType;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.VersementService;

@Service
@Transactional
public class VersementServiceImpl implements VersementService{
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private VersementRepository versementRepository;
	
	@Autowired
	private CompteRepository compteRepository;

	@Override
	public List<Versement> getAllVersements() {
		return versementRepository.findAll();
	}

	@Override
	public Versement getVersement(Long id) {
		return versementRepository.findById(id).get();
	}

	@Override
	public Versement createVersement(VersementDto versementDto) throws CompteNonExistantException {
		
		Compte compte = compteRepository.findByRib(versementDto.getRib());
		
		if(compte == null) throw new CompteNonExistantException("the Compte with the Rib: " + versementDto.getRib() +
				" doesn't existe");
		
		compte.setSolde(compte.getSolde().add(versementDto.getMontantVirement()));
		compteRepository.save(compte);
		
		Versement versement = new Versement();
		versement.setCompteBeneficiaire(compte);
		versement.setDateExecution(new Date());
		versement.setMontantVirement(versementDto.getMontantVirement());
		versement.setNom_prenom_emetteur(versementDto.getNom_prenom_emetteur());
		
		Audit audit = new Audit();
        audit.setMessage("Virement depuis " + versementDto.getNom_prenom_emetteur() + " vers " + compte.getNrCompte()
        		+ " d\'un montant de " + versementDto.getMontantVirement()
                .toString());
        audit.setEventType(EventType.VERSEMENT);
        auditService.createAudit(audit);
		
		return versementRepository.save(versement);
	}

	@Override
	public Versement updateVersement(Versement versement, Long id) {
		Versement oldVersement = versementRepository.findById(id).get();
		oldVersement.setCompteBeneficiaire(versement.getCompteBeneficiaire());
		oldVersement.setDateExecution(versement.getDateExecution());
		oldVersement.setMontantVirement(versement.getMontantVirement());
		oldVersement.setMotifVersement(versement.getMotifVersement());
		oldVersement.setNom_prenom_emetteur(versement.getNom_prenom_emetteur());
		return oldVersement;
	}

	@Override
	public Versement deleteVersement(Long id) {
		Versement oldVersement = versementRepository.findById(id).get();
		versementRepository.deleteById(id);
		return oldVersement;
	}
	
}
