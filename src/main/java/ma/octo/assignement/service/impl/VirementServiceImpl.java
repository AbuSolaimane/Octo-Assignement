package ma.octo.assignement.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.entity.Audit;
import ma.octo.assignement.entity.Compte;
import ma.octo.assignement.entity.Virement;
import ma.octo.assignement.entity.util.EventType;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.VirementService;

@Service
@Transactional
public class VirementServiceImpl implements VirementService{

	public static final int MONTANT_MAXIMAL = 10000;
	@Autowired
	private VirementRepository virementRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private AuditService auditService;
	@Override
	
	public List<Virement> getAllVirements() {
		return virementRepository.findAll();
	}

	@Override
	public Virement getVirement(Long id) {
		return virementRepository.findById(id).get();
	}

	@Override
	public Virement createVirement(Virement virement) {
		return virementRepository.save(virement);
	}

	@Override
	public Virement updateVirement(Virement virement, Long id) {
		Virement oldVirement = virementRepository.findById(id).get();
		oldVirement.setCompteBeneficiaire(virement.getCompteBeneficiaire());
		oldVirement.setCompteEmetteur(virement.getCompteEmetteur());
		oldVirement.setDateExecution(virement.getDateExecution());
		oldVirement.setMontantVirement(virement.getMontantVirement());
		oldVirement.setMotifVirement(virement.getMotifVirement());
		return virementRepository.save(oldVirement);
	}

	@Override
	public Virement deleteVirement(Long id) {
		Virement oldVirement = virementRepository.findById(id).get();
		virementRepository.deleteById(id);
		return oldVirement;
	}

	@Override
	public Virement createTransaction(VirementDto virementDto)
			throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
		
		Date date = new Date();
		List<Virement> virements = virementRepository.findByDateExecutionAnd(date);
		
		BigDecimal montantTotal = new BigDecimal(0);
		for (Virement virement : virements) {
			montantTotal = montantTotal.add(virement.getMontantVirement());
		}
		
        Compte compteEmetteur = compteRepository.findByNrCompte(virementDto.getNrCompteEmetteur());
        Compte compteBeneficiaire = compteRepository.findByNrCompte(virementDto.getNrCompteBeneficiaire());

        if (compteEmetteur == null || compteBeneficiaire == null) {
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (virementDto.getMontantVirement().equals(null) || virementDto.getMontantVirement().intValue() == 0) {
            throw new TransactionException("Montant vide");
        } else if (virementDto.getMontantVirement().intValue() < 10) {
            throw new TransactionException("Montant minimal de virement non atteint");
        } else if (virementDto.getMontantVirement().intValue() > MONTANT_MAXIMAL) {
            throw new TransactionException("Montant maximal de virement dépassé");
        }

        if (virementDto.getMotif().length() < 0) {
            throw new TransactionException("Motif vide");
        }
        
        if (compteEmetteur.getSolde().intValue() - virementDto.getMontantVirement().intValue() < 0) {
        	throw new SoldeDisponibleInsuffisantException("Solde insuffisant pour l\'utilisateur");
        }

        compteEmetteur.setSolde(compteEmetteur.getSolde().subtract(virementDto.getMontantVirement()));
        compteRepository.save(compteEmetteur);

        compteBeneficiaire.setSolde(new BigDecimal(compteBeneficiaire.getSolde().intValue() + virementDto.getMontantVirement().intValue()));
        compteRepository.save(compteBeneficiaire);

        Virement virement = new Virement();
        virement.setDateExecution(virementDto.getDate());
        virement.setCompteBeneficiaire(compteBeneficiaire);
        virement.setCompteEmetteur(compteEmetteur);
        virement.setMontantVirement(virementDto.getMontantVirement());

        virementRepository.save(virement);
        
        Audit audit = new Audit();
        audit.setMessage("Virement depuis " + virementDto.getNrCompteEmetteur() + " vers " + virementDto
                .getNrCompteBeneficiaire() + " d\'un montant de " + virementDto.getMontantVirement()
                .toString());
        audit.setEventType(EventType.VIREMENT);
        auditService.createAudit(audit);
        return virement;
	}
	
	

}
