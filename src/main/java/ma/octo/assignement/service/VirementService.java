package ma.octo.assignement.service;

import java.util.List;

import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.entity.Virement;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;

public interface VirementService {
	List<Virement> getAllVirements();
	Virement getVirement(Long id);
	Virement createVirement(Virement virement);
	Virement updateVirement(Virement virement, Long id);
	Virement deleteVirement(Long id);
	Virement createTransaction(VirementDto virementDto)
			throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException;
}
