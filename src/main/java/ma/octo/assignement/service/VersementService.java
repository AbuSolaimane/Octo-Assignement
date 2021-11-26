package ma.octo.assignement.service;

import java.util.List;

import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.entity.Versement;
import ma.octo.assignement.exceptions.CompteNonExistantException;

public interface VersementService {
	List<Versement> getAllVersements();
	Versement getVersement(Long id);
	Versement updateVersement(Versement versement, Long id);
	Versement deleteVersement(Long id);
	Versement createVersement(VersementDto versementDto) throws CompteNonExistantException;
}
